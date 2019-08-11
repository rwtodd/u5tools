module org.rwtodd.u5tools.core {
	requires transitive java.desktop;
	exports org.rwtodd.u5tools.spi;
	uses org.rwtodd.u5tools.spi.Tool;
}