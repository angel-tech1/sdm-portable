# sdm-portable
A StrongDM desktop application for Linux implemented in Compose Multiplatform.

## Motivation

At the time of writing there's no official StrongDM desktop app for Linux platforms. I needed an application
that allowed me to do the most basic tasks without having to use the commands line each time and manually look for 
the resource I am trying to connect to from a very (very) long list.

This application does only that:
- show all available resources
- allow you to look for a service using one or more strings (i.e. "mysql,usa,dev")
- connect and/or disconnect with a click

## Pre-requisites
1. The SDM CLI is already [installed](https://www.strongdm.com/docs/desktop/installation/linux/) in your system.
2. You have already [logged in](https://www.strongdm.com/docs/cli/#login-and-logout) using the command line.

This application reads the output of the `sdm status` command in order to identify the available services. It assumes 
that the first column in this output represents the resource identifier and uses it when executing the connect and 
disconnect commands. It also looks for "connected" and "not connected" in each line to identify the connection status. If
any of these assumptions are not correct according to the output generated by your system then the 
application may not work as intended.

## Installation
On Debian based systems simply download and install the latest deb located in the releases section with a double click, 
or using any of the following commands:

### Using dpkg
`sudo dpkg -i sdm-portable_X.X.X_amd64.deb`

### Using apt-get
`sudo apt-get install ./sdm-portable_X.X.X_amd64.deb`

## Building a binary

### Pre-requisited

- Java SDK 17+
- fakeroot (`sudo apt-get update && sudo apt-get install fakeroot`)

### Debian compilation

1. Build the debian binary `./gradlew packageDeb`
2. Locate the .deb file under the build/compose/binaries folder.

### Other platforms
Use the standard Compose Multiplatform package commands.

## License
GNU GENERAL PUBLIC LICENSE
