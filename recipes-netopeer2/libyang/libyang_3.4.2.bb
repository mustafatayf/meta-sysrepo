SUMMARY = "YANG data modelling language parser and toolkit"
DESCRIPTION = "libyang is YANG data modelling language parser and toolkit written (and providing API) in C. The library is used e.g. in libnetconf2, Netopeer2 or sysrepo projects."
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9bb3d334294e8719f41c531e28a9a697"

SRC_URI = "git://github.com/CESNET/libyang.git;protocol=https;branch=master"

SRCREV = "526e3ffd1e93ca5874675b48f4027de6150ce92f"
#PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "libpcre2"

FILES:${PN} += "/usr/share/yang/modules/libyang/*"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release "

BBCLASSEXTEND = "native nativesdk"
