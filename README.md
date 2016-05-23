# Multipass
_Permissions plugin approved by Leeloo Dallas_

Multipass is a permissions plugin for Nukkit that provide a universal permission system for Nukkit.

## Features
* Individual permissions and group
* Group inheritance
* World related permissions and group
* API for another plugins

## Permissions
* multipass.admin - use multipass commands

## Commands
### General commands
#### Show help
* **/perm help**

#### Reload configuration
* **/perm reload**

#### Check permissions
* **/perm check <user>**
This command check permissions of online player.

### Configure user permissions
#### Show user info
* **/user <user>**
Shows full user info

#### Set user permission
* **/user <user> setperm [world] <permission.node>*
* **/user <user> sp [world] <permission.node>*
* **/user <user> addperm [world] <permission.node>*
* **/user <user> ap [world] <permission.node>*
Set permission node for user. If you need to define permission node 
that related to world you need to define (additionally) world name.
For neagative permissions you need to use symbol "-" before permission.
Example:
``/user fromgate sp -fantastic.permission``

  настройка группы
  + /group <id> create | remove

  + /group <id> setperm
  + removeperm
  + setgroup
  + addgroup
  + removegroup

  + /group <id> setprfix | setsuffix

  /world <id> setgroup <group>
  /world <id> 
  /world setworld <world>


