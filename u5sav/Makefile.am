u5execdir=$(cmddir)
u5exec_SCRIPTS=sav
CLEANFILES=sav

replace=sed -e 's|@cmddir[@]|$(cmddir)|g' \
	-e 's|@lua[@]|$(LUA)|g'

sav: Makefile sav.in
	rm -f $@ $@.tmp
	$(replace) $@.in > $@.tmp
	chmod 755 $@.tmp
	mv $@.tmp $@
