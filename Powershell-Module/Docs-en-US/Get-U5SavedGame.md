---
external help file: RWTodd.Ultima5Tools-help.xml
Module Name: RWTodd.Ultima5Tools
online version:
schema: 2.0.0
---

# Get-U5SavedGame

## SYNOPSIS
Read an Ultima V saved game file from disk, and return a data structure of its contents.

## SYNTAX

```
Get-U5SavedGame [[-LiteralPath] <String>] [<CommonParameters>]
```

## DESCRIPTION
An Ultima V saved game file is read from disk into an object, which is returned.  The object contains the main stats for all the saved characters, and the party's inventory.  These can all be changed interactively in powershell, and then written back to disk with the `Update-U5SavedGame` cmdlet.

Stats returned by the game include: Armor, Weapons, Spells, Potions, all the quest items (Sandalwood Box, the Shards, etc.), all the basic supplies (Food, Gems, Gold, etc.), Karma, and stats for each character.

The cmdlet understands the DOS SAVED.GAM/INIT.GAM format.  It is unlikely to work with other ports of the game.

## EXAMPLES

### Example 1
```powershell
PS C:\U5> $gm = Get-U5SavedGame
PS C:\U5> $gm.Characters | Select-Object Name,Status,CurHP

Name     Status   CurHP
----     ------   -----
Richard  Good        60
Shamino  Poisoned     5
Iolo     Good        90
Mariah   Good         2
Geoffrey Good        90
Jaana    Good        60
Julia    Good        60
Dupre    Good        90
Katrina  Good       150
Sentri   Good        60
Gwenno   Good        90
Johne    Good        90
Gorn     Good        60
Maxwell  Good        30
Toshi    Good        30
Saduj    Good       120
```

The default file (.\SAVED.GAM) is read in, and the characters' Name, Status, and HP are displayed.  These can all be changed on the command line, and then later saved to disk via the `Update-U5SavedGame` cmdlet.

### Example 2
```powershell
PS C:\U5> $gm = Get-U5SavedGame
PS C:\U5> $gm.Characters[0].Name = "LINDA"
PS C:\U5> $gm.SkullKeys = 21
PS C:\U5> $gm | Update-U5SavedGame -OutPath changed.gam
```

The default file (.\SAVED.GAM) is read in, and the first character's name is changed, and the party is given 21 skull keys.  Then, the modified game is written to disk as `changed.gam`.

## PARAMETERS

### -LiteralPath
The path to the saved game file to load.  It defaults to the actual file used by the game, "SAVED.GAM".

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 0
Default value: "SAVED.GAM"
Accept pipeline input: False
Accept wildcard characters: False
```

### CommonParameters
This cmdlet supports the common parameters: -Debug, -ErrorAction, -ErrorVariable, -InformationAction, -InformationVariable, -OutVariable, -OutBuffer, -PipelineVariable, -Verbose, -WarningAction, and -WarningVariable. For more information, see [about_CommonParameters](http://go.microsoft.com/fwlink/?LinkID=113216).

## INPUTS

### None

## OUTPUTS

### System.Object

## NOTES

## RELATED LINKS
