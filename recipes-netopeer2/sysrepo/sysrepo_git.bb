# Recipe created by recipetool
SUMMARY = "YANG-based configuration and operational state data store for Unix/Linux applications."
DESCRIPTION = ""
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef345f161efb68c3836e6f5648b2312f"

SRC_URI = "git://github.com/sysrepo/sysrepo.git;protocol=https;branch=master \
           file://sysrepod.service"
#           file://0001-Hardcode-correct-path-to-tar-binary.patch \
#           ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', \
#                'file://sysrepo','', d)} \
#           ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', \
#                'file://sysrepod.service','', d)}"
#           file://sysrepod.service"

PV = "2.11.7+git"
SRCREV = "a6f309eb9601b9ef28e95a74655d7e9eac3dcc7a"

S = "${WORKDIR}/git"

DEPENDS = "libyang protobuf protobuf-c protobuf-c-native libredblack libev libnetconf2"

FILES:${PN} += "${datadir}/yang/* ${libdir}/sysrepo-plugind/* /lib/systemd/system/*"


inherit cmake pkgconfig python3native python3-dir
#inherit ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}


# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release -DBUILD_EXAMPLES:String=False -DENABLE_TESTS:String=False -DREPOSITORY_LOC:PATH=/etc/sysrepo  -DCALL_TARGET_BINS_DIRECTLY=False -DGEN_LANGUAGE_BINDINGS:String=False "

BBCLASSEXTEND = "native nativesdk"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "sysrepod.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

RDEPENDS:${PN} += "tar"

do_install:append () {
    install -d ${D}${sysconfdir}/sysrepo/data/notifications
    install -d ${D}${sysconfdir}/sysrepo/yang
    install -d ${D}${sysconfdir}/init.d
#    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
#        install -m 0775 ${WORKDIR}/sysrepo ${D}${sysconfdir}/init.d/
#        install -d ${D}${libdir}/sysrepo/plugins
#    fi

#    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/sysrepod.service ${D}${systemd_system_unitdir}
#    fi
}
