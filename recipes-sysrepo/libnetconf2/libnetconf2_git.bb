SUMMARY = "libnetconf2 is a NETCONF library in C intended for building NETCONF clients and servers"
DESCRIPTION = "The library provides functions to connect NETCONF client and server to each other via SSH and to send, receive and process NETCONF messages."
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=08a5578c9bab06fb2ae84284630b973f"

SRC_URI = "git://github.com/CESNET/libnetconf2.git;protocol=https;branch=devel"

#SRCREV = "4514f16d71665a1b2c7ad29908474ef67526ed07"
#PV = "2.1.37+git{SRCPV}"
SRCREV = "${AUTOREV}"
PV = "+git${SRCPV}"

S = "${WORKDIR}/git"


DEPENDS = "libssh openssl libyang curl cmocka libgcrypt"

FILES:${PN} += " /usr/share/yang/modules/libnetconf2/* "

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=RELWITHDEBINFO "

BBCLASSEXTEND = "native nativesdk"
