---
external help file: RWTodd.Ultima5Tools-help.xml
Module Name: RWTodd.Ultima5Tools
online version:
schema: 2.0.0
---

# Set-U5CommonSettings

## SYNOPSIS
Set commonly-used settings in Ultima V saved games.

## SYNTAX

### MultiStep
```
Set-U5CommonSettings -InputObject <PSObject> [-Healthy] [-BasicSupplies] [-Food <Int32>] [-Gold <Int32>]
 [-Spells <Int32>] [-Potions <Int32>] [-QuestItems] [<CommonParameters>]
```

### Convenience
```
Set-U5CommonSettings [-OnDisk] [-LiteralPath <String>] [-Healthy] [-BasicSupplies] [-Food <Int32>]
 [-Gold <Int32>] [-GetSpells] [-GetPotions] [-QuestItems] [<CommonParameters>]
```

## DESCRIPTION
This cmdlet is a convenience to set commonly-used values in an Ultima V saved game file.  It can take an object created by `Get-U5SavedGame`, or if `-OnDisk` is set, it can read and write a saved game file itself.  Switches (such as `-BasicSupplies`) control which values are set.  This is usually more convenient than manipulating all the values in a saved game object manually.


## EXAMPLES

### Example 1
```powershell
PS C:\U5> Get-U5SavedGame | Set-U5CommonSettings -BasicSupplies | Update-U5SavedGame -BackupOriginal
```

This command reads in the default saved game ("SAVED.GAM"), updates key supplies like food and keys to make sure the party has some of each, and then saves it to disk.  A backup of the original is made as "SAVED.GAM.BAK".

### Example 2
```powershell
PS C:\U5> Set-U5CommonSettings -OnDisk -BasicSupplies 
```

Note that, when no additional manipulation of the file is needed, it is simpler to use the `-OnDisk` interface.  This example is equivalent to example 1.  The reason to use the more complicated form in example 1 is if you want to examine or further manipulate the saved game object returned from `Get-U5SavedGame`.

## PARAMETERS

### -BasicSupplies
Ensures that the party has at least 400 Food, at least 1000 Gold, at least 75 Karma, and at least 40 Gems, Keys, SkullKeys, and Torches.

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -Food
Gives the party the given amount of food (0-9999).  Overrides `-BasicSupplies` if that is also in effect.

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -Gold
Gives the party the given amount of gold (0-9999).  Overrides `-BasicSupplies` if that is also in effect.

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -Healthy
Ensures that all party members are in (G)ood condition and that their HP is equal to their maximum HP.

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -InputObject
This is the saved game object to modify, usually loaded via `Get-U5SavedGame`.

```yaml
Type: PSObject
Parameter Sets: MultiStep
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

### -LiteralPath
The path to an on-disk saved game file to modify in place.

```yaml
Type: String
Parameter Sets: Convenience
Aliases:

Required: False
Position: Named
Default value: "SAVED.GAM"
Accept pipeline input: False
Accept wildcard characters: False
```

### -OnDisk
Indicates that, rather than taking an object as input, the cmdlet should load and save a saved-game file from disk.

```yaml
Type: SwitchParameter
Parameter Sets: Convenience
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -Potions
Ensures that the party has at least the given number of all potion types.

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -QuestItems
Ensures that the party has the Scepter, Crown, Amulet, Sandalwood Box, and the three Shards.

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -Spells
Ensures that the party has at least the given number of all spell types.

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### CommonParameters
This cmdlet supports the common parameters: -Debug, -ErrorAction, -ErrorVariable, -InformationAction, -InformationVariable, -OutVariable, -OutBuffer, -PipelineVariable, -Verbose, -WarningAction, and -WarningVariable. For more information, see [about_CommonParameters](http://go.microsoft.com/fwlink/?LinkID=113216).

## INPUTS

### System.Management.Automation.PSObject

## OUTPUTS

### System.Object
## NOTES

## RELATED LINKS
