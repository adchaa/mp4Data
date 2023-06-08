# mp4Metadata

**work in progress**

this project aim to extract from mp4 file all the metadata within it

## atomType

every attribute is private you should get it value with getter.

example:

Majorbrand ---> getMajorBrand()

all the atom have Size :

```java
private long Size;

public long getSize() {
        return Size;
}
```

### 1) ftyp

- Majorbrand
- Minorbrand
- Compatiblebrand

```java
    private String Majorbrand;
    private long Minorbrand;
    private ArrayList<String> Compatiblebrand;
```

### 2) moov :

![moov](https://developer.apple.com/library/archive/documentation/QuickTime/QTFF/art/qtff_08.gif)

#### A) mvhd :

- Version
- CreationTime
- ModificationTime
- Duration
- PreferredRate
- PreferredVolume
- MatrixStructure
- PreviewTime
- PreviewDuration
- PosterTime
- SelectionTime
- SelectionDuration
- CureentTime
- NextTrackID

```java
    private int Version;
    private Date CreationTime;
    private Date ModificationTime;
    private long Duration;
    private float PreferredRate;
    private float PreferredVolume;
    private float[][] MatrixStructure;
    private long PreviewTime;
    private long PreviewDuration;
    private long PosterTime;
    private long SelectionTime;
    private long SelectionDuration;
    private long CurrentTime;
    private long NextTrackID;
```

#### B) trak :

##### a) tkhd :

- Version
- CreationTime
- Modification
- TrackID
- Duration
- Layer
- Volume
- Width
- Height

```java
    private int Version;
    private Date CreationTime;
    private Date ModificationTime;
    private long TrackID;
    private long Duration;
    private long Layer;
    private float Volume;
    private float Width;
    private float Height;
```
