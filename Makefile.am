SUBDIRS=u5sav u5runes
bin_SCRIPTS=u5tools
CLEANFILES=u5tools
EXTRA_DIST=LICENSE u5tools.in

replace=sed -e 's|@cmddir[@]|$(cmddir)|g'

u5tools: Makefile u5tools.in
	rm -f $@ $@.tmp
	$(replace) $@.in > $@.tmp
	chmod 755 $@.tmp
	mv $@.tmp $@
