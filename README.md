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
* **/perm check \<user>**

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


