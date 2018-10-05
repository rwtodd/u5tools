SUBDIRS=u5sav
bin_SCRIPTS=ultima5
CLEANFILES=ultima5

replace=sed -e 's|@cmddir[@]|$(cmddir)|g'

ultima5: Makefile ultima5.in
	rm -f $@ $@.tmp
	$(replace) $@.in > $@.tmp
	chmod 755 $@.tmp
	mv $@.tmp $@
