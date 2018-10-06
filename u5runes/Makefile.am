u5execdir=$(cmddir)
u5exec_SCRIPTS=runes
CLEANFILES=runes
EXTRA_DIST=runes.in

replace=sed -e 's|@cmddir[@]|$(cmddir)|g' \
	-e 's|@lua[@]|$(LUA)|g'

runes: Makefile runes.in
	rm -f $@ $@.tmp
	$(replace) $@.in > $@.tmp
	chmod 755 $@.tmp
	mv $@.tmp $@
