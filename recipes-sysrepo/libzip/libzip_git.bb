SUMMARY = "YANG data modelling language parser and toolkit"
DESCRIPTION = "libzip is a C library for reading, creating, and modifying zip archives."
HOMEPAGE = "https://libzip.org/"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d8a9d2078f35e61cf1122ccd440687cf"

SRC_URI = "git://github.com/nih-at/libzip.git;protocol=https;branch=main"

SRCREV = "${AUTOREV}"
PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "zlib bzip2 xz"

inherit cmake

PACKAGECONFIG[lzma] = "-DENABLE_LZMA=ON,-DENABLE_LZMA=OFF,xz"

PACKAGECONFIG ?= "lzma"
