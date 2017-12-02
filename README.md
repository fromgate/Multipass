# Multipass
_Permissions plugin approved by Leeloo Dallas_

Multipass is a permissions plugin that provides a universal permission system for Nukkit.

## Download
[**Project page (outdated, use dev builds)**](http://nukkit.ru/resources/multipass.101/) 

[**Latest Dev Build**  ![CircleCI](https://circleci.com/gh/fromgate/Multipass.svg?style=shield)](https://circleci.com/gh/fromgate/Multipass)

## Features
* Individual permissions and group
* Group inheritance
* World related permissions and group
* API for another plugins

## Please help us to translate Multipass
Currently Multipass mostly translated to Russian, Turkish, French, Korean.
If you miss your language, you can help with translation. We use Crowdin to translate Multipass. Join our translation project [here](https://crowdin.com/project/multipass/invite)

[![Crowdin](https://d322cqt584bo4o.cloudfront.net/multipass/localized.svg)](https://crowdin.com/project/multipass)

## Permissions
>**multipass.admin** - use multipass commands

## Commands
### General commands
#### Show help
>**/perm help**

#### Reload configuration
>**/perm reload**

#### Refresh permissions
>**/perm refresh**<br>
>**/perm update**<br>
>**/perm recalculate**

Recalculate permissions of all users. I hope you'll never need to use
this permissions.

#### Check permissions
>**/perm check \<user\>**

Simply check permissions

This command checks permissions of online player.

#### Export permissions to file
>**/perm export \[\<filename\>\]**

Export permissions (all group and users) into single file. 

Example:

``/perm export data`` - save all permissions in file _data.yml_

#### Import permissions from file
>**/perm import \[overwrite\] \[\<filename\>\]**<br>
>**/perm import \[over\] \[\<filename\>\]**

Import permissions from file. User "overwrite" parameter to delete all 
groups and users before import. 

Examples:

``/perm import data`` - load permissions from file _data.yml_
``/perm import overwrite`` - delete all permissions than load from file _import.yml_

### Configure user permissions
#### Show user info
>**/user \<user\>**

Shows full user info (Groups, permissions, prefixes, suffixes)

#### Remove user
>**/user remove \<user\>**<br>
>**/user rmv \<user\>**<br>
>**/user delete \<user\>**<br>
>**/user del \<user\>**

Delete user information. If player is online user record will be recreated
and user will add to default group (if it defined in config).


#### Set user permission
>**/user \<user\> setperm \[\<world\>\] \<permission.node\>**<br>
>**/user \<user\> sp \[\<world\>\] \<permission.node\>**<br>
>**/user \<user\> addperm \[\<world\>\] \<permission.node\>**<br>
>**/user \<user\> ap \[\<world\>\] \<permission.node\>**

Set permission node for user. If you need to define permission node 
that related to world you need to define (additionally) world name.
For neagative permissions you need to use symbol "-" before permission.

Example:

``/user fromgate sp -fantastic.permission``

#### Remove user permission
>**/user \<user\> removeperm \[\<world\>\] \<permission.node\>**<br>
>**/user \<user\> rmvperm \[\<world\>\] \<permission.node\>**<br>
>**/user \<user\> rperm \[\<world\>\] \<permission.node\>**<br>
>**/user \<user\> rp \[\<world\>\] \<permission.node\>**

Remove user permission.

Example:

``/user fromgate rp fantastic.permission``

#### Set user group
>**/user \<user\> setgroup \[\<world\>\] \<group\>**<br>
>**/user \<user\> setgrp \[\<world\>\] \<group\>**<br>
>**/user \<user\> sgrp \[\<world\>\] \<group\>**<br>
>**/user \<user\> sg \[\<world\>\] \<group\>**

Set user group. All other groups will be removed.

Example:

``/user fromgate sg vip``

#### Add user group
>**/user \<user\> addgroup \[\<world\>\] \<group\>**<br>
>**/user \<user\> addgrp \[\<world\>\] \<group\>**<br>
>**/user \<user\> agrp \[\<world\>\] \<group\>**<br>
>**/user \<user\> ag \[\<world\>\] \<group\>**

Set user group. This will not affect other groups

Example:

``/user fromgate ag admin``

#### Remove user from group
>**/user \<user\> removegroup \[\<world\>\] \<group\>**<br>
>**/user \<user\> rmvgrp \[\<world\>\] \<group\>**<br>
>**/user \<user\> rgrp \[\<world\>\] \<group\>**<br>
>**/user \<user\> rg \[\<world\>\] \<group\>**

Remove user from group.

#### Set user prefix/suffix
##### Set user prefix

>**/user \<user\> setprefix \<prefix\>**<br>
>**/user \<user\> prefix \<prefix\>**<br>
>**/user \<user\> px \<prefix\>**

##### Set user suffix

>**/user \<user\> setsuffix \<suffix\>**<br>
>**/user \<user\> suffix \<suffix\>**<br>
>**/user \<user\> sx \<suffix\>**



### Configure groups
#### Show group info
>**/group \[\<group\>\]**

Display list of groups or show info about provided group

#### Create new group
>**/group create \<group\>**<br>
>**/group new \<group\>**

Create new group

#### Remove group
>**/group remove \<group\>**<br>
>**/group rmv \<group\>**<br>
>**/group delete \<group\>**<br>
>**/group del \<group\>**

Delete group. This will not affect users' files so if you will create
a new group with same name all previous members will join new group too.

#### Set permission
>**/group \<group\> addperm \[\<world\>\] \<permission\>**<br>
>**/group \<group\> setperm \[\<world\>\] \<permission\>**<br>
>**/group \<group\> aperm \[\<world\>\] \<permission\>**<br>
>**/group \<group\> sp \[\<world\>\] \<permission\>**

Set permission node for group. If you need to define permission node 
that related to world you need to define (additionally) world name.
For negative permissions you need to use symbol "-" before permission.
These permissions will affect all group members.

Examples:

``/group vip sp -fantastic.permission``
``/group vip sp nether_world magic.permission``

#### Remove permission
>**/group \<group\> removeperm \[\<world\>\] \<permission\>**<br>
>**/group \<group\> rmvperm \[\<world\>\] \<permission\>**<br>
>**/group \<group\> rperm \[\<world\>\] \<permission\>**<br>
>**/group \<group\> rp \[\<world\>\] \<permission\>**

Remove group permission.
Example:
``/group vip rp fantastic.permission``

#### Set group
>**/group \<group\> setgroup \[\<world\>\] \<group2\>**<br>
>**/group \<group\> setgrp \[\<world\>\] \<group2\>**<br>
>**/group \<group\> sgrp \[\<world\>\] \<group2\>**<br>
>**/group \<group\> sg \[\<world\>\] \<group2\>**

Move group into "parent group". This group will inherit all permissions provided by parent group.
Another group (defined previously) will be removed.
Players - members of target group will have permissions defined by target and parent group.
 
#### Add group
>**/group \<group\> addgroup \[\<world\>\] \<group2\>**<br>
>**/group \<group\> addgrp \[\<world\>\] \<group2\>**<br>
>**/group \<group\> agrp \[\<world\>\] \<group2\>**<br>
>**/group \<group\> ag \[\<world\>\] \<group2\>**

Add additional parent group to target group.
Players - members of target group will have permissions defined by all groups.

#### Remove linked group
>**/group \<group\> removegroup \[\<world\>\] \<group2\>**<br>
>**/group \<group\> rmvgrp \[\<world\>\] \<group2\>**<br>
>**/group \<group\> rgrp \[\<world\>\] \<group2\>**<br>
>**/group \<group\> rg \[\<world\>\] \<group2\>**

Remove group from group group2.

#### Set group prefix/suffix
##### Set group prefix

>**/group \<group\> setprefix \<prefix\>**<br>
>**/group \<group\> prefix \<prefix\>**<br>
>**/group \<group\> px \<prefix\>**

##### Set user suffix

>**/group \<group\> setsuffix \<suffix\>**<br>
>**/group \<group\> suffix \<suffix\>**<br>
>**/group \<group\> sx \<suffix\>**

## Configuration files
### Mulitpass configuration

File: **config.yml**

```
# Multipass, Nukkit permission system
#
general:
# Messages language. Supported languages:
# eng - English,
# rus - Russian,
# tur - Turkish.
# You can help translate plugin: https://crowdin.com/project/multipass/invite
  language: default
# Save translation file
  language-save: false
# Debug mode. Usually you don't need to turn it on
  debug: false
permissions:
  group:
# Default group. All new players will automagically join this group
    default-group: default
# Default priority value for groups
    default-priority: 10
# Use group name to add additional permission. If this option is enabled all
# group members will have additional permission: permission.group.<groupId>
    group-as-permission: true
  user:
# Default priority value for users
    default-priority: 100
  multiworld:
# Enable multiworld support
    enable: false
# Enable world mirroring
    mirrors:
      world: nether_world, end_world
storage:
# Database type:
# - YAML - yaml files
# - DATABASE - MySQL or SQLite. DbLib plugins is required: http://nukkit.ru/resources/dblib.14/
  type: YAML
# Auto-update interval (for multi-server system). If this values is not equal to '0'
# groups and permissions will be automatically updated.
# Time format: 1s  - 1 second, 1m - 1 minute, 1h - one hour
  auto-update-interval: '0'
  database:
# Database configuration:
# - DEFAULT - default database, configured in plugin DbLib
# - SQLITE  - custom SQLite file (filename provided below)
# - MYSQL   - custom MySQL database (configuration provided below)
    source: DEFAULT
    sqlite:
# Custom SQLite file name
      file: permissions.db
# Custom MySQL configuration
    mysql:
      host: localhost
      port: 3306
      database: database
      username: nukkit
      password: tikkun
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
  worlds:
    world1:
      groups:
      - worldgroup
      permissions: 
      - world.permission
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
worlds: 
  world1:
    groups:
    - worldgroup
    permissions: 
    - world.permission
```