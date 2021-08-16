<#
Reference: https://wiki.ultimacodex.com/wiki/Ultima_V_internal_formats#SAVED.GAM
#>

$ArmorsList = @( <# 0x21A #>
    "LeatherHelm", "ChainCoif", "IronHelm", "SpikedHelm", "SmallShield", "LargeShield", "SpikedShield", "MagicShield", 
    "JewelledShield", "Cloth", "Leather", "RingMail", "Scale", "Chain", "Plate", "MysticArmour" )
$WeaponsList = @( <# 0x22A #>
    "Dagger", "Sling", "Club", "FlamingOil", "MainGauche", "Spear", "ThrowingAxe", "ShortSword", "Mace", "MorningStar", 
    "Bow", "Arrows", "Crossbow", "Quarrels", "LongSword", "Hammer2H", "Axe2H", "Sword2H", "Halberd", "ChaosSword",
    "MagicBow", "SilverSword", "MagicAxe", "GlassSword", "JeweledSword", "MysticSword" )
$SpellsList = @( <# 0x24A #>
    "InLor", "GravPor", "AnZu", "AnNox", "Mani", "AnYlem", "AnSanct", "AnXenCorp", "RelHur", "InWis", "KalXen", 
    "InXenMani", "VasLor", "VasFlam", "InFlamGrav", "InNoxGrav", "InZuGrav", "InPor", "AnGrav", "InSanct", 
    "InSanctGrav", "UusPor", "DesPor", "WisQuas", "InBetXen", "AnExPor", "InExPor", "VasMani", "InZu", "RelTym", 
    "InVasPorYlem", "QuasAnWis", "InAn", "WisAnYlem", "AnXenEx", "RelXenBet", "SanctLor", "XenCorp", "InQuasXen", 
    "InQuasWis", "InNoxHur", "InQuasCorp", "InManiCorp", "KalXenCorp", "InVasGravCorp", "InFlamHur", "VasRelPor", 
    "AnTym" )
$ScrollsList = @( <# 0x27A #>
    "VasLor", "RelHur", "InSanct", "InAn", "InQuasWis", "KalXenCorp", "InManiCorp", "AnTym" )
$PotionsList = @( <# 0x282 #>
    "Blue", "Yellow", "Red", "Green", "Orange", "Purple", "Black", "White")
$MiscMagicList = @( <# 0x244 #>
    "InvisibilityRing", "ProtectionRing", "RegenerationRing", "AmuletOfTurning" )
$ReagentsList = @( <# 0x2AA #>
    "SulphurousAsh", "Ginseng", "Garlic", "SpiderSilk", "BloodMoss", "BlackPearl", "Nightshade", "MandrakeRoot" )

function read-u16([byte[]] $src, [int] $idx) {
    [int]$src[$idx] -bor ([int]$src[$idx + 1] -shl 8)
}

function write-u16([byte[]] $src, [int] $idx, [int] $value) {
    $value = [Math]::Min($value, 9999)
    $src[$idx] = [byte]($value -band 0xff)
    $src[$idx + 1] = [byte](($value -band 0xff00) -shr 8)
}

function read-bytes {
    param(
        [Parameter(ValueFromPipeline = $true)]
        [PSCustomObject]$Object,
        [string[]]$Names,
        [byte[]]$Data,
        [int]$StartIdx
    )
    foreach ($name in $Names) {
        Add-Member -InputObject $Object -NotePropertyName $name -NotePropertyValue $Data[$StartIdx] -TypeName "System.Byte"
        $StartIdx += 1
    }
    $Object
}

function write-bytes {
    param(
        [PSCustomObject]$InputObject,
        [string[]]$Names,
        [byte[]]$Data,
        [int]$StartIdx
    )
    foreach ($name in $Names) {
        $Data[$StartIdx] = [byte]($InputObject.$name)
        $StartIdx += 1
    }
}

function Get-U5SavedGame {
    [CmdletBinding()]
    param(
        [Parameter(Position = 0)]
        [ValidateScript( { (Get-Item $_).Length -eq 4192 }, ErrorMessage = "{0} file length should be 4192")]
        [string] $LiteralPath = "SAVED.GAM"
    )

    $SavedGame = Get-Item $LiteralPath
    Write-Verbose "Reading $SavedGame"
    $data = [System.IO.File]::ReadAllBytes($SavedGame)

    # get the characters
    $chars = 0..15 | ForEach-Object {
        $idx = 2 + ($_ * 0x20)
        [PSCustomObject]@{
            Name         = [System.Text.Encoding]::ASCII.GetString($data[$idx..($idx + 8)]).Trim([char]0)
            Class        = switch ([char]($data[$idx + 0x0a])) {
                "A" { "Avatar" }
                "B" { "Bard" }
                "F" { "Fighter" }
                "M" { "Mage" }
                default { [string]$_ }
            }
            Status       = switch ([char]($data[$idx + 0x0b])) {
                "G" { "Good" }
                "D" { "Dead" }
                "P" { "Poisoned" }
                "S" { "Sleeping" }
                default { [string]$_ }
            }
            Strength     = $data[$idx + 0x0c]
            Dexterity    = $data[$idx + 0x0d]
            Intelligence = $data[$idx + 0x0e]
            CurrentMP    = $data[$idx + 0x0f]
            CurHP        = read-u16 $data ($idx + 0x10)
            MaxHP        = read-u16 $data ($idx + 0x12)
            Experience   = read-u16 $data ($idx + 0x14)
            Level        = $data[$idx + 0x16]
            Helmet       = $data[$idx + 0x19]
            Armor        = $data[$idx + 0x1a]
            WeaponLeft   = $data[$idx + 0x1b] 
            WeaponRight  = $data[$idx + 0x1c]
            Ring         = $data[$idx + 0x1d]
            Amulet       = $data[$idx + 0x1e]
            InnParty     = $data[$idx + 0x1f]
        }
    }
    
    # armors
    $armor = [PSCustomObject]@{
        SpikedCollar = $data[0x248]
        Ankh         = $data[0x249]
    } | read-bytes -Names $ArmorsList -StartIdx 0x21A -Data $data

    $weapons = New-Object -TypeName PSCustomObject | read-bytes -Data $data -StartIdx 0x22A -Names $WeaponsList
    $spells = New-Object -TypeName PSCustomObject | read-bytes -Data $data -StartIdx 0x24A -Names $SpellsList
    $scrolls = New-Object -TypeName PSCustomObject | read-bytes -Data $data -StartIdx 0x27A -Names $ScrollsList
    $potions = New-Object -TypeName PSCustomObject | read-bytes -Data $data -StartIdx 0x282 -Names $PotionsList
    $miscMagic = New-Object -TypeName PSCustomObject | read-bytes -Data $data -StartIdx 0x244 -Names $MiscMagicList
    $reagents = New-Object -TypeName PSCustomObject | read-bytes -Data $data -StartIdx 0x2AA -Names $ReagentsList
   
    # build an object of the relevant data
    [PSCustomObject]@{
        File           = $SavedGame
        RawData        = $data
        Characters     = $chars
        Food           = read-u16 $data 0x202
        Gold           = read-u16 $data 0x204
        Keys           = $data[0x206]
        Gems           = $data[0x207]
        Torches        = $data[0x208]
        Grapple        = $data[0x209] -eq 0xff
        MagicCarpets   = $data[0x20a]
        SkullKeys      = $data[0x20b]
        Amulet         = $data[0x20d] -eq 0xff
        Crown          = $data[0x20e] -eq 0xff
        Scepter        = $data[0x20f] -eq 0xff
        ShardFalsehood = $data[0x210] -eq 0xff
        ShardHatred    = $data[0x211] -eq 0xff
        ShardCowardice = $data[0x212] -eq 0xff
        SpyGlasses     = $data[0x214]
        HMSCapePlans   = $data[0x215] -eq 0xff
        Sextants       = $data[0x216]
        PocketWatch    = $data[0x217] -eq 0xff
        BlackBadge     = $data[0x218] -eq 0xff
        SandalwoodBox  = $data[0x219] -eq 0xff
        Karma          = $data[0x2e2]
        Weapons        = $weapons
        Armor          = $armor
        MiscMagic      = $miscMagic
        Spells         = $spells
        Scrolls        = $scrolls
        Potions        = $potions
        Reagents       = $reagents
    }
}

function Update-U5SavedGame {
    [CmdletBinding(SupportsShouldProcess)]
    param(
        [Parameter()]
        [string] $OutPath,

        [Parameter(Mandatory = $true, ValueFromPipeline=$true)]
        [PSCustomObject] $InputObject,

        [Parameter()]
        [switch] $BackupOriginal
    )

    # determine the output file...
    $OutPath = $PSCmdlet.GetUnresolvedProviderPathFromPSPath(($OutPath.Length -gt 0) ? $OutPath : $InputObject.File)
    Write-Verbose "Writing to $OutPath"

    if ($BackupOriginal.IsPresent -and (Test-Path $OutPath)) {
        if ($PSCmdlet.ShouldProcess($OutPath,"Backing Up")) {
            Copy-Item $OutPath ($OutPath + ".BAK") -Force
        }
    }

    $data = $InputObject.RawData

    # write the characters
    0..15 | ForEach-Object {
        $char = $InputObject.Characters[$_]
        $idx = 2 + ($_ * 0x20)
        [array]::copy(([System.Text.Encoding]::ASCII.GetBytes($char.Name) + (, [byte]0) * 8), 0, $data, $idx, 8)
        $data[$idx + 0x0a] = [System.Text.Encoding]::ASCII.GetBytes($char.Class.ToUpper())[0]
        $data[$idx + 0x0b] = [System.Text.Encoding]::ASCII.GetBytes($char.Status.ToUpper())[0]
        $data[$idx + 0x0c] = $char.Strength     
        $data[$idx + 0x0d] = $char.Dexterity    
        $data[$idx + 0x0e] = $char.Intelligence 
        $data[$idx + 0x0f] = $char.CurrentMP    
        write-u16 $data ($idx + 0x10) $char.CurHP        
        write-u16 $data ($idx + 0x12) $char.MaxHP        
        write-u16 $data ($idx + 0x14) $char.Experience   
        $data[$idx + 0x16] = $char.Level        
        $data[$idx + 0x19] = $char.Helmet       
        $data[$idx + 0x1a] = $char.Armor        
        $data[$idx + 0x1b] = $char.WeaponLeft   
        $data[$idx + 0x1c] = $char.WeaponRight  
        $data[$idx + 0x1d] = $char.Ring         
        $data[$idx + 0x1e] = $char.Amulet       
        $data[$idx + 0x1f] = $char.InnParty     
    }

    # armors
    $data[0x248] = $InputObject.Armor.SpikedCollar
    $data[0x249] = $InputObject.Armor.Ankh
    write-bytes -Names $ArmorsList -StartIdx 0x21A -Data $data -InputObject $InputObject.Armor
    write-bytes -Names $WeaponsList -StartIdx 0x22A -Data $data -InputObject $InputObject.Weapons
    write-bytes -Names $SpellsList -StartIdx 0x24A -Data $data -InputObject $InputObject.Spells
    write-bytes -Names $ScrollsList -StartIdx 0x27A -Data $data -InputObject $InputObject.Scrolls
    write-bytes -Names $PotionsList -StartIdx 0x282 -Data $data -InputObject $InputObject.Potions
    write-bytes -Names $MiscMagicList -StartIdx 0x244 -Data $data -InputObject $InputObject.MiscMagic
    write-bytes -Names $ReagentsList -StartIdx 0x2AA -Data $data -InputObject $InputObject.Reagents
    
    # build an object of the relevant data
    write-u16 $data 0x202 $InputObject.Food
    write-u16 $data 0x204 $InputObject.Gold
    $data[0x206] = $InputObject.Keys          
    $data[0x207] = $InputObject.Gems          
    $data[0x208] = $InputObject.Torches       
    $data[0x209] = $InputObject.Grapple        ? 0xff : 0x00
    $data[0x20a] = $InputObject.MagicCarpets  
    $data[0x20b] = $InputObject.SkullKeys     
    $data[0x20d] = $InputObject.Amulet         ? 0xff : 0x00
    $data[0x20e] = $InputObject.Crown          ? 0xff : 0x00
    $data[0x20f] = $InputObject.Scepter        ? 0xff : 0x00
    $data[0x210] = $InputObject.ShardFalsehood ? 0xff : 0x00
    $data[0x211] = $InputObject.ShardHatred    ? 0xff : 0x00
    $data[0x212] = $InputObject.ShardCowardice ? 0xff : 0x00
    $data[0x214] = $InputObject.SpyGlasses    
    $data[0x215] = $InputObject.HMSCapePlans   ? 0xff : 0x00
    $data[0x216] = $InputObject.Sextants      
    $data[0x217] = $InputObject.PocketWatch    ? 0xff : 0x00
    $data[0x218] = $InputObject.BlackBadge     ? 0xff : 0x00
    $data[0x219] = $InputObject.SandalwoodBox  ? 0xff : 0x00
    $data[0x2e2] = $InputObject.Karma         

    if ($PSCmdlet.ShouldProcess($OutPath,"Writing Output")) {
        [System.IO.File]::WriteAllBytes($OutPath, $data)
    }
}

function Set-U5CommonSettings {
    [CmdletBinding()]
    param(
        [Parameter(ValueFromPipeline=$true,Mandatory=$true,ParameterSetName="MultiStep")]
        [PSCustomObject]$InputObject,
        [Parameter(Mandatory=$true,ParameterSetName="Convenience")]
        [switch] $OnDisk,
        [Parameter(ParameterSetName="Convenience")]
        [string] $LiteralPath="SAVED.GAM",
        [switch] $Healthy,
        [switch] $BasicSupplies,
        [Parameter()]
        [ValidateRange(0,9999)]
        [int] $Food,
        [Parameter()]
        [ValidateRange(0,9999)]
        [int] $Gold,
        [Parameter()]
        [ValidateRange(0,99)]
        [int] $Spells,
        [Parameter()]
        [ValidateRange(0,99)]
        [int] $Potions,
        [switch] $QuestItems
    )
    If($OnDisk.IsPresent) {
        # If we are on the convenience path, load the game....
        $InputObject = Get-U5SavedGame -LiteralPath $LiteralPath
    }
    if($Healthy.IsPresent) {
        Write-Verbose "Making all characters (G)ood with their Max HP"
        foreach($char in $InputObject.Characters) {
            $char.Status = "Good"
            $char.CurHP = $char.MaxHP
        }
    }
    if($BasicSupplies.IsPresent) {
        Write-Verbose "Making sure the party has at least 40 Gems, Torches, and Keys"
        $InputObject.Gems      = [Math]::Max($InputObject.Gems,40)
        $InputObject.Keys      = [Math]::Max($InputObject.Keys,40)
        $InputObject.SkullKeys = [Math]::Max($InputObject.SkullKeys,40)
        $InputObject.Torches   = [Math]::Max($InputObject.Torches,40)
        Write-Verbose "Making sure the party has at least 400 Food"
        $InputObject.Food = [Math]::Max($InputObject.Food,400)
        Write-Verbose "Making sure the party has at least 1000 Gold"
        $InputObject.Gold = [Math]::Max($InputObject.Gold,1000)
        Write-Verbose "Making sure karma isn't in the toilet"
        $InputObject.Karma = [Math]::Max($InputObject.Karma,75)
    }
    if($QuestItems.IsPresent) {
        Write-Verbose "Giving the party the important quest items (Amulet+Crown+Scepter+Shards+Sandalwood Box)"
        $InputObject.Amulet = $true
        $InputObject.Crown = $true
        $InputObject.Scepter = $true
        $InputObject.ShardCowardice = $true
        $InputObject.ShardFalsehood = $true
        $InputObject.ShardHatred = $true
        $InputObject.SandalwoodBox = $true
    }    
    if($PSBoundParameters.ContainsKey("Food")) {
        Write-Verbose "Setting food to $Food"
        $InputObject.Food = $Food
    }
    if($PSBoundParameters.ContainsKey("Gold")) {
        Write-Verbose "Setting gold to $Gold"
        $InputObject.Gold = $Gold
    }
    if($PSBoundParameters.ContainsKey("Spells")) {
        Write-Verbose "Getting at least $Spells of all spells"
        $InputObject.Spells | Get-Member -Type NoteProperty | foreach-object {
            $sname = $_.Name
            $InputObject.Spells.$sname = [Math]::Max($InputObject.Spells.$sname, $Spells)
        }
    }
    if($PSBoundParameters.ContainsKey("Potions")) {
        Write-Verbose "Getting at least $Potions of all potions"
        $InputObject.Potions | Get-Member -Type NoteProperty | foreach-object {
            $pname = $_.Name
            $InputObject.Potions.$pname = [Math]::Max($InputObject.Potions.$pname, $Potions)
        }
    }

    # If we are on the convenience path, save the game now...
    if($OnDisk.IsPresent) {
        Update-U5SavedGame -SavedGame $InputObject -BackupOriginal
        return
    }
    $InputObject
}
