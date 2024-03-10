# Recipe created by recipetool
SUMMARY = "YANG-based configuration and operational state data store for Unix/Linux applications."
DESCRIPTION = ""
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef345f161efb68c3836e6f5648b2312f"

SRC_URI = "git://github.com/sysrepo/sysrepo.git;protocol=https;branch=master file://sysrepo"

SRCREV = "${AUTOREV}"
PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "libyang tar"

FILES:${PN} += " /usr/share/yang/modules/sysrepo/* /usr/lib/sysrepo-plugind/* "

inherit cmake pkgconfig systemd

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=RELWITHDEBINFO -DSYSREPO_UMASK=007 -DSYSREPO_GROUP=sysrepo -DNACM_SRMON_DATA_PERM=660 "

BBCLASSEXTEND = "native nativesdk" 

do_install:append () {
    install -d ${D}/etc/init.d
    install -m 0775 ${WORKDIR}/sysrepo ${D}/etc/init.d/
}

