SUMMARY = "libnetconf2 is a NETCONF library in C intended for building NETCONF clients and servers"
DESCRIPTION = "The library provides functions to connect NETCONF client and server to each other via SSH and to send, receive and process NETCONF messages."
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=08a5578c9bab06fb2ae84284630b973f"

SRC_URI = "git://github.com/CESNET/libnetconf2.git;protocol=https;branch=master"

SRCREV = "53c97bca3b90a5711b64a54885a09a2bdfa45a66"
#PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "libssh curl openssl libyang libxcrypt libpam"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
#EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release -DLIBYANG_INCLUDE_DIR=/usr/include -DLIBYANG_LIBRARY=/usr/lib "
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release "

BBCLASSEXTEND = "native nativesdk"
FILES:${PN}= "/usr/share/yang/ /usr/lib/"

#From https://github.com/lf-connectivity/open-mplane/blob/main/meta-mplane/recipes-support/netconf2/netconf2_1.1.24.bb
#OECMAKE_EXTRA_append = " -DENABLE_TLS=ON"
#OECMAKE_EXTRA_append = " -DENABLE_SSH=ON"
