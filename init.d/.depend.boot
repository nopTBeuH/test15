TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh plymouth-log screen-cleanup apparmor udev cryptdisks cryptdisks-early hwclock.sh networking checkroot.sh lvm2 iscsid urandom mountdevsubfs.sh checkfs.sh open-iscsi mountall.sh checkroot-bootclean.sh kmod mountnfs-bootclean.sh mountnfs.sh bootmisc.sh procps mountall-bootclean.sh
INTERACTIVE = console-setup udev cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
networking: resolvconf mountkernfs.sh urandom procps
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
iscsid: networking
urandom: hwclock.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
open-iscsi: networking iscsid
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
bootmisc.sh: mountnfs-bootclean.sh udev checkroot-bootclean.sh mountall-bootclean.sh
procps: mountkernfs.sh udev
mountall-bootclean.sh: mountall.sh
