SUMMARY = "YANG data modelling language parser and toolkit"
DESCRIPTION = "libyang is YANG data modelling language parser and toolkit written (and providing API) in C. The library is used e.g. in libnetconf2, Netopeer2 or sysrepo projects."
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9bb3d334294e8719f41c531e28a9a697"

SRC_URI = "git://github.com/CESNET/libyang.git;protocol=https;branch=devel"

SRCREV = "8b0b910a2dcb7360cb5b0aaefbd1338271d50946"
PV = "2.1.111+git{SRCPV}"
#SRCREV = "${AUTOREV}"
#PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "libpcre2"

FILES:${PN} += " /usr/share/yang/* "

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=RELWITHDEBINFO "

BBCLASSEXTEND = "native nativesdk"
