SUBDIRS=u5sav
bin_SCRIPTS=u5tools
CLEANFILES=u5tools

replace=sed -e 's|@cmddir[@]|$(cmddir)|g'

u5tools: Makefile u5tools.in
	rm -f $@ $@.tmp
	$(replace) $@.in > $@.tmp
	chmod 755 $@.tmp
	mv $@.tmp $@
