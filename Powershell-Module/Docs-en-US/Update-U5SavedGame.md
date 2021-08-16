---
external help file: RWTodd.Ultima5Tools-help.xml
Module Name: RWTodd.Ultima5Tools
online version:
schema: 2.0.0
---

# Update-U5SavedGame

## SYNOPSIS
Writes a changed Ultima V saved game to disk for use in the game.

## SYNTAX

```
Update-U5SavedGame [[-OutPath] <String>] [-InputObject] <PSObject> [-BackupOriginal] [-WhatIf] [-Confirm]
 [<CommonParameters>]
```

## DESCRIPTION
Writes an Ultima V saved game object to disk, optionally backing up the original file if it exists.  The input saved game will typically originate from a call to `Get-U5SavedGame.`  If an output path is not given, the original filename stored in the input object is used.

## EXAMPLES

### Example 1
```powershell
PS C:\> Get-U5SavedGame | Set-U5CommonSettings -Food 800 | Update-U5SavedGame -BackupOriginal
```
Reads `SAVED.GAM`, sets the food to 800, and saves it back to `SAVED.GAM`, backing up the original as `SAVED.GAM.BAK`.

## PARAMETERS

### -BackupOriginal
If set, a copy of the output file is made prior to being overwritten.  The backup has the same name as the original, but with a ".BAK" extension added.

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

### -Confirm
Prompts you for confirmation before allowing the program to write changes.

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -OutPath
The name of the output file.  If not given, the original source filename stored in the input object is used.

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### -InputObject
The object representing the saved game, typically obtained from a prior call to `Get-U5SavedGame`.  This can be passed in on the pipeline.

```yaml
Type: PSObject
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

### -WhatIf
Goes through the motions of executing the command, without actually changing anything on disk.

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

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
