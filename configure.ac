#                                               -*- Autoconf -*-
# Process this file with autoconf to produce a configure script.

AC_PREREQ([2.69])
AC_INIT([u5tools], [1.0], [https://github.com/rwtodd/u5tools])

AC_CONFIG_MACRO_DIRS([m4])

AC_CONFIG_SRCDIR([u5tools.in])
# AC_CONFIG_HEADERS([config.h])

AM_INIT_AUTOMAKE([foreign])

# Checks for programs.
# AC_PROG_CC
AX_PROG_LUA([5.3])

# my install subdir
AC_SUBST([cmddir],[${bindir}/u5tools-cmd])

# Checks for libraries.

# Checks for header files.
# AC_CHECK_HEADERS([fcntl.h stdint.h unistd.h])

AC_CONFIG_FILES([Makefile u5sav/Makefile])
AC_OUTPUT
