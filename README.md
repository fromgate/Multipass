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

#### Refresh permissions
* **/perm reload**
Recalculate permissions of all users. I hope you'll never need to use
this permissions.

#### Check permissions
* **/perm check \<user>**
Simply check permissions

This command check permissions of online player.

### Configure user permissions
#### Show user info
* **/user \<user>**

Shows full user info (Groups, permissions, prefixes, suffixes)

#### Set user permission
* **/user \<user> setperm \[world] \<permission.node>**
* **/user \<user> sp \[world] \<permission.node>**
* **/user \<user> addperm \[world] \<permission.node>**
* **/user \<user> ap \[world] \<permission.node>**

Set permission node for user. If you need to define permission node 
that related to world you need to define (additionally) world name.
For neagative permissions you need to use symbol "-" before permission.
Example:
``/user fromgate sp -fantastic.permission``

#### Remove user permission
* **/user \<user> removeperm \[world] \<permission.node>**
* **/user \<user> rmvperm \[world] \<permission.node>**
* **/user \<user> rperm \[world] \<permission.node>**
* **/user \<user> rp \[world] \<permission.node>**

Remove user permission.
Example:
``/user fromgate rp fantastic.permission``

#### Set user group
* **/user \<user> setgroup \[world] \<group>**
* **/user \<user> setgrp \[world] \<group>**
* **/user \<user> sgrp \[world] \<group>**
* **/user \<user> sg \[world] \<group>**

Set user group. All other groups will be removed.
Example:
``/user fromgate sg vip``

#### Add user group
* **/user \<user> addgroup \[world] \<group>**
* **/user \<user> addgrp \[world] \<group>**
* **/user \<user> agrp \[world] \<group>**
* **/user \<user> ag \[world] \<group>**

Set user group. This will not affect other groups
Example:
``/user fromgate ag admin``

#### Remove user from group
* **/user \<user> removegroup \[world] \<group>**
* **/user \<user> rmvgrp \[world] \<group>**
* **/user \<user> rgrp \[world] \<group>**
* **/user \<user> rg \[world] \<group>**

Remove user from group.

#### Set user prefix/suffix
##### Set user prefix

* **/user \<user> setprefix \<prefix>**
* **/user \<user> prefix \<prefix>**
* **/user \<user> px \<prefix>**

##### Set user suffix

* **/user \<user> setsuffix \<suffix>**
* **/user \<user> suffix \<suffix>**
* **/user \<user> sx \<suffix>**



### Configure groups
#### Show group info
* **/group \[\<group>]**

Display list of groups or show info about provided group

#### Create new group
* **/group create \<group>**
* **/group new \<group>**

Create new group

#### Remove group
* **/group remove \<group>**
* **/group rmv \<group>**
* **/group delete \<group>**
* **/group del \<group>**

Delete group. This will not affect users' files so if you will create
a new group with same name all previous members will join new group too.

#### Set permission
* **/group \<group> addperm \[world] \<permission>**
* **/group \<group> setperm \[world] \<permission>**
* **/group \<group> aperm \[world] \<permission>**
* **/group \<group> sp \[world] \<permission>**

Set permission node for group. If you need to define permission node 
that related to world you need to define (additionally) world name.
For negative permissions you need to use symbol "-" before permission.
These permissions will affect all group members.

Examples:
``/group vip sp -fantastic.permission``
``/group vip sp nether_world magic.permission``

#### Remove permission
* **/group \<group> removeperm \[world] \<permission>**
* **/group \<group> rmvperm \[world] \<permission>**
* **/group \<group> rperm \[world] \<permission>**
* **/group \<group> rp \[world] \<permission>**

Remove group permission.
Example:
``/group vip rp fantastic.permission``

#### Set group
* **/group \<group> setgroup \[world] \<group2>**
* **/group \<group> setgrp \[world] \<group2>**
* **/group \<group> sgrp \[world] \<group2>**
* **/group \<group> sg \[world] \<group2>**

Move group into "parent group". This group will inherit all permissions provided by parent group.
Another group (defined previously) will be removed.
Players - members of target group will have permissions defined by target and parent group.
 
#### Add group
* **/group \<group> addgroup \[world] \<group2>**
* **/group \<group> addgrp \[world] \<group2>**
* **/group \<group> agrp \[world] \<group2>**
* **/group \<group> ag \[world] \<group2>**

Add additional parent group to target group.
Players - members of target group will have permissions defined by all groups.

#### Remove linked group
* **/group \<group> removegroup \[world] \<group2>**
* **/group \<group> rmvgrp \[world] \<group2>**
* **/group \<group> rgrp \[world] \<group2>**
* **/group \<group> rg \[world] \<group2>**

Remove group from group group2.

#### Set group prefix/suffix
##### Set group prefix

* **/group \<group> setprefix \<prefix>**
* **/group \<group> prefix \<prefix>**
* **/group \<group> px \<prefix>**

##### Set user suffix

* **/group \<group> setsuffix \<suffix>**
* **/group \<group> suffix \<suffix>**
* **/group \<group> sx \<suffix>**

## Configuration files
### Mulitpass configuration

File: **config.yml**

```
general:
# Language. Supproted values:
# default - Default language, configured in nukkit.yml
# eng - English
# rus - Russian
  language: default
# Save language file. Use it if you going to edit messages
# or if you going to translate language files
  language-save: false
# Debug mode. Usually you don't need to use it
  debug: false

permissions:
  group:
# Default group. If player have no any group or permissions defined 
# he will be member of this group
    default-group: default
# Default value of group permissions priority
    default-priority: 10
  user:
# Default value of user permissions priority  
    default-priority: 100
  multiworld:
# Enable multiworld support
    enable: false
# Configure mirroring of worlds
    mirrors:
      world: nether_world, end_world
```

### Group configuration

File : **groups.yml**

```
# Group configuration example:
#
#default:
#  groups:                # List of group (all group defined in this file)
#  - vip
#  permissions:           # List of permissions.
#  - plugin.vip
#  - plugin.test
#  prefix: '&6[VIP]'      # Group prefix, used by third-party plugins
#  suffix: ''             # Group suffix, used by third-party plugins
#  priority: 10           # Group priority
#  worlds:                # List of per-world permissions and groups
#    end:
#      groups:
#      - vip_end
#      permissions:
#      - -plugin.vip      # Negative permission starts with "-"
#      - -plugin.test
default:
  groups: []
  permissions:
  - default.permission
  prefix: '[guest]'
  suffix: ''
  priority: 0
  worlds: {}
```

### User configuration

User configuration files stored in `/plugins/users/` folder.
For example: `/plugins/users/fromgate.yml`

Here is example of user configuration file:
```
groups:
- default
- testgroup
permissions: 
- test.permission
- -default.permission
prefix: '[vip]'
suffix: ''
priority: 100
worlds: {}
```