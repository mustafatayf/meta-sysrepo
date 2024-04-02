#!/bin/bash

export NP2_MODULE_DIR="/usr/share/yang/modules/netopeer2/"
export NP2_MODULE_PERMS="600"
export NP2_MODULE_OWNER="root"
export NP2_MODULE_GROUP="root"
export SYSREPOCTL_EXECUTABLE="/usr/bin/sysrepoctl"                                                        
export SYSREPOCFG_EXECUTABLE="/usr/bin/sysrepocfg"
/usr/share/netopeer2/setup.sh
/usr/share/netopeer2/merge_hostkey.sh
/usr/share/netopeer2/merge_config.sh

sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-wg4-features.yang
sysrepoctl -c o-ran-wg4-features -e SUPERVISION-WITH-SESSION-ID
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-supervision.yang

sysrepoctl -i /usr/share/yang/modules/netopeer2/iana-if-type.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/iana-hardware.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/ietf-hardware.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-interfaces.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-usermgmt.yang -I users.xml
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-processing-element.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-compression-factors.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-module-cap.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-hardware.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-common-yang-types.yang
sysrepoctl -i /usr/share/yang/modules/netopeer2/o-ran-uplane-conf.yang
