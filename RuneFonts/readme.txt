Ultima Runic Fonts with Ligatures
---------------------------------

These fonts are based on the Runes-C.ttf one can find on the net[1]. I added
ligatures to the font so that you can type words more naturally. For example,
you can type `thee` and wind up with just the `th` and `ee` glyphs.  This
works by default in a lot of editors and terminals. In some, such as MS Word,
you have to go into the font settings and enable ligatures in the advanced
tab (just ask the internet how, if you don't know).

After getting ligatures to work, the main concern I had was how to handle
inter-word dots. (In many places runes are used, there is a dot between the
words rather than blank space).

Initially, I tried using the actual glyph for spaces for the dots. You would think
this would be ideal, but there are two issues: First, this causes problems
in MS Word, which really wants to insert a space at the end of the current
paragraph, for whatever reason.  Second, if you want blank space, it is harder
to do when your space glyph has a dot in it. If you like this idea, there is a
`dotspace` variant of the files here.

Because of the above, I use hyphens (-) as the inter-word dot.  I chose the hypen
because it will not interfere with spellchecking in most programs to have hyphens
between every word. If you want something that looks like an actual hyphen, just
insert a mathematical minus sign (Unicode + 2212). An inconvenience, to be sure.

The *.sfd files are FontForge[1] files.

[1]: https://www.dafont.com/ultima-runes.font
[2]: https://fontforge.github.io/en-US/
