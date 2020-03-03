# EAN-13 exercies

Read about EAN 13 [on wikipedia](https://en.wikipedia.org/wiki/International_Article_Number).

Implement `se.vbgt.ean13.EAN13` so it passes the tests below (and it will be a start for generating barcode images).

Modules, in text, will be represented with a bar `|` and absence of modules with a space ` `.

## EAN13NumberTest

* Must be 13 digits
* Must be correct check digit

## EAN13GroupingTest

* Test of grouping of first part of modules


## EAN13ModuleTest1

* Test encoding digit to modules, depending on group

## EAN13ModuleTest2

* Testing length
* Testing markers
  * start
  * end
  * middle

## EAN13ModuleTest3

* Test of actual complete modules of barcodes

## Bonus

Actually generate a PNG image of the barcode, remember the quiet space!
