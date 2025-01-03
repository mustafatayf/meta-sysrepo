SUMMARY = "Netopeer2 is a set of tools implementing network configuration tools based on the NETCONF Protocol."
DESCRIPTION = "Netopeer2 is based on the new generation of the NETCONF and YANG libraries - libyang and libnetconf2. The Netopeer server uses sysrepo as a NETCONF datastore implementation."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41daedff0b24958b2eba4f9086d782e1"

#SRC_URI = "git://github.com/CESNET/Netopeer2.git;protocol=https;branch=master file://netopeer2-server"
SRC_URI = "git://github.com/CESNET/Netopeer2.git;protocol=https;branch=master \
          ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', \
	        'file://netopeer2-server', '', d)} \
          ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', \
	        'file://netopeer2-serverd.service', '', d)} \
          "

PV = "2.2.31+git${SRCPV}"
SRCREV = "1bd72ec706a798b3b3855401cf50d497b4cda76b"


S = "${WORKDIR}/git"
UNPACKDIR = "${WORKDIR}/.."

DEPENDS = "libyang libnetconf2 sysrepo curl"
RDEPENDS:${PN} += "bash curl"

FILES:${PN} += "${datadir}/yang* ${datadir}/netopeer2/* ${libdir}/sysrepo-plugind/*"
#FILES:${PN} += "/usr/share/yang* /usr/share/netopeer2/* /usr/lib/sysrepo-plugind/* /usr/lib/systemd/system/*"
#FILES:${PN} += "/usr/share/yang* /usr/share/netopeer2/* /usr/lib/sysrepo-plugind/*"

inherit cmake pkgconfig
inherit ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}


# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
#EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE:String=Release -DINSTALL_MODULES=OFF -DGENERATE_HOSTKEY=OFF -DMERGE_LISTEN_CONFIG=OFF"
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE:String=Release -DINSTALL_MODULES=OFF -DGENERATE_HOSTKEY=OFF -DMERGE_LISTEN_CONFIG=OFF -DSYSREPOCTL_EXECUTABLE:PATH=${bindir}/sysrepoctl -DSYSREPOCFG_EXECUTABLE:PATH=${bindir}/sysrepocfg "

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
#EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE:String=Release -DINSTALL_MODULES=OFF -DGENERATE_HOSTKEY=OFF -DMERGE_LISTEN_CONFIG:String=OFF -DSYSREPO_SETUP:String=OFF -DNP2_MODULE_PERMS:String=600 -DNP2_MODULE_OWNER:String=root -DNP2_MODULE_GROUP:String=root -DSYSREPOCTL_EXECUTABLE:PATH=/usr/local/bin/sysrepoctl -DSYSREPOCFG_EXECUTABLE:PATH=/usr/local/bin/sysrepocfg -DSYSREPO_SETUP:String=OFF "
# -DOPENSSL_ROOT_DIR:PATH=/usr/lib/openssl/ -DOPENSSL_CRYPTO_LIBRARY:PATH=/usr/lib/libcrypto.so -DOPENSSL_EXECUTABLE=/usr/bin/openssl "
# EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release -DINSTALL_MODULES:String=OFF -DGENERATE_HOSTKEY:String=OFF -DMERGE_LISTEN_CONFIG:String=OFF -DSYSREPOCTL_EXECUTABLE:PATH=/usr/bin/sysrepoctl -DSYSREPOCFG_EXECUTABLE:PATH=/usr/bin/sysrepocfg "

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "netopeer2-serverd.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

do_install:append () {
    install -d ${D}${sysconfdir}/netopeer2/scripts
    install -o root -g root ${S}/scripts/setup.sh ${D}${sysconfdir}/netopeer2/scripts/setup.sh
    install -o root -g root ${S}/scripts/merge_hostkey.sh ${D}${sysconfdir}/netopeer2/scripts/merge_hostkey.sh
    install -o root -g root ${S}/scripts/merge_config.sh ${D}${sysconfdir}/netopeer2/scripts/merge_config.sh
    install -d ${D}${sysconfdir}/netopeer2
    install -d ${D}${sysconfdir}/init.d
    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -m 0755 ${UNPACKDIR}/netopeer2-server ${D}${sysconfdir}/init.d/
    fi
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${UNPACKDIR}/netopeer2-serverd.service ${D}${systemd_system_unitdir}
    fi
}


#do_install:append () {
#    install -d ${D}/etc/netopeer2/scripts
#    install -o root -g root ${S}/scripts/setup.sh ${D}/etc/netopeer2/scripts/setup.sh
#    install -o root -g root ${S}/scripts/merge_hostkey.sh ${D}/etc/netopeer2/scripts/merge_hostkey.sh
#    install -o root -g root ${S}/scripts/merge_config.sh ${D}/etc/netopeer2/scripts/merge_config.sh
#    install -d ${D}/etc/netopeer2
#    install -d ${D}/etc/init.d
#    install -m 0755 ${WORKDIR}/netopeer2-server ${D}/etc/init.d/
#}

