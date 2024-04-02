SUMMARY = "Netopeer2 is a set of tools implementing network configuration tools based on the NETCONF Protocol."
DESCRIPTION = "Netopeer2 is based on the new generation of the NETCONF and YANG libraries - libyang and libnetconf2. The Netopeer server uses sysrepo as a NETCONF datastore implementation."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41daedff0b24958b2eba4f9086d782e1"

SRC_URI = "git://github.com/CESNET/Netopeer2.git;protocol=https;branch=devel file://netopeer2-server"

SRCREV = "3f6e921e46a5b78e93df13f60cfc88ea22b96287"
PV = "2.1.71+git{SRCPV}"
#SRCREV = "${AUTOREV}"
#PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS = "libyang libnetconf2 sysrepo"

RDEPENDS:${PN} += "bash curl"

FILES:${PN} += " /usr/share/yang/modules/netopeer2/* /usr/share/netopeer2/* "

inherit cmake pkgconfig systemd 

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=RELWITHDEBINFO -DSYSREPO_SETUP=OFF -DPIDFILE_PREFIX=/run  -DSERVER_DIR=%{_libdir}/netopeer2-server "

do_install:append () {
    install -d ${D}/etc/netopeer2/scripts
    install -o root -g root ${S}/scripts/setup.sh ${D}/etc/netopeer2/scripts/common.sh
    install -o root -g root ${S}/scripts/setup.sh ${D}/etc/netopeer2/scripts/setup.sh
    install -o root -g root ${S}/scripts/setup.sh ${D}/etc/netopeer2/scripts/remove.sh
    install -o root -g root ${S}/scripts/merge_hostkey.sh ${D}/etc/netopeer2/scripts/merge_hostkey.sh
    install -o root -g root ${S}/scripts/merge_config.sh ${D}/etc/netopeer2/scripts/merge_config.sh
    install -d ${D}/etc/netopeer2
    install -d ${D}/etc/init.d
    install -m 0755 ${WORKDIR}/netopeer2-server ${D}/etc/init.d/
}
      
