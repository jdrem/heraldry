## Simple Heraldry Illustrator
This repository contains some simple tools to create images of heraldic shields. Currently, you can create shields using the basic colors and metals as well as the variations fo ermine.
Most to the traditional ordinaries are supported: fess, pale, chief, bend, bend sinister, saltire and cross:

<img src="images/ordinaries.svg" width="550" height="330">

Several basic charges can be used: annulet, crescent, fleur-de-lis, heart, lozenge, martlet, mullet, rose, roundel:

<img src="images/charges.svg" width="675" height="350">

Multiple charges can be arranged in a variety of ways:

<img src="images/arrangement-of-charges.svg" width="550" height="350">

To create a shield, use the [__Builder__](src/main/java/net/remgant/heraldry/Builder.java) class to construct the shield and a [__FileWriter__](src/main/java/net/remgant/heraldry/FileWriter.java) instance to save to a file. 

For instance, for the basic cas of "or a fess guile":

```Java
  FileWriter fileWriter = new PNGFileWriter(200, 250);
  Builder builder = new Buidler(fileWriter);
  builder.add(new Shield(Tincture.OR);
  builder.add(new Fess(Tincture.GULES);
  builder.build(fw -> {
    fw.writeToFile("or-a-fess-gules.png"):
  });
```
Which would produce:

<img src="images/or-a-fess-gules.svg" width="200" height="250">
When placing charges on the filed, you need to specify not only the tincture, but the relative location of the field and the scaling of the charge. For "sable, three bezants in pale":

```Java
    FileWriter fileWriter = new PNGFileWriter(200, 250);
    Builder builder = new Buidler(fileWriter);
    builder.add(new Shield(Tincture.SABLE);
    builder.add(new Roundel(Tincture.OR, Shield.Position.PALE_TOP, 1.0));
    builder.add(new Roundel(Tincture.OR, Shield.Position.CENTER, 1.0));
    builder.add(new Roundel(Tincture.OR, Shield.Position.PALE_BOTTOM, 1.0));
    builder.build(fw -> {
       fw.writeToFile("sable-three-bezants-in-pale.png"):
    });
```
This produces:

<img src="images/sable-three-bezants-in-pale.svg" width="200" height="250">

Builder also has convenience methods so you could do the first example like this instead:
```Java
  FileWriter fileWriter = new PNGFileWriter(200, 250);
  Builder builder = new Buidler(fileWriter);
  builder.fieldOf(Tincture.OR)
    .fess(Tincture.GULES)
    .build(fw -> {
        fw.writeToFile("or-a-fess-gules.png"):
   });
```
In addition to various plain fields, the various divisions of the field are availabe. 

There are two demo programs.

First there is [__DrawRandom__](src/main/java/net/remgant/heraldry/DrawRandom.java) which creates a bunch of random shields. It's a good example of how to 
contruct various types of shield but there are a few cases where it violates the color/metal rule or the field and charge don't contrast enough.

Second is [__DrawEverything__](src/main/java/net/remgant/heraldry/DrawEverything.java) which generates examples of most of the ordinaries and charges. It was used to 
generate the sample images above.
