# Ultima V RPG Utilities
(a powershell module)

## Cmdlets

### Get-U5SavedGame

Parses an Ultima-V saved game file and returns an object of its contents.  This can be inspected and modified by the user.

### Update-U5SavedGame

Takes an object (such as that returned by `Get-U5SavedGame`) and updates an Ultima-V saved game file with whatever is in the object.  The idea is that you can `Get-U5SavedGame`, modify the object, and then `Update-U5SavedGame` to make the required changes.

### Set-U5CommonSettings

Allows you to update the most-often changed attributes (like HP, Food...) without having to directly manipulate the data.
It can modify the object from `Get-U5SavedGame`, or it can grab the saved game directly from disk (when used with `-OnDisk`).
