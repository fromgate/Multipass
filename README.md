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
* **/user \<user> setprefix \<group>**
* **/user \<user> prefix \<group>**
* **/user \<user> prefix \<group>**

Set user prefix


* **/user \<user> setsuffix \<group>**
* **/user \<user> suffix \<group>**
* **/user \<user> sx \<group>**

Set user suffix

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
* **/group \<user> removegroup \[world] \<group>**
* **/group \<user> rmvgrp \[world] \<group>**
* **/group \<user> rgrp \[world] \<group>**
* **/group \<user> rg \[world] \<group>**

Remove user from group.