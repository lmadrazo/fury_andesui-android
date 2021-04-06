# AndesThumbnail

AndesThumbnail is a container that display an image, logo or icon to complement content and reinforce meaning.
[See Andes UI component in frontify](https://company-161429.frontify.com/d/kxHCRixezmfK/n-a#/components/thumbnail-1589997379)

```kotlin
class AndesThumbnail : FrameLayout
```

Basic Sample Programatically

```kotlin
AndesThumbnail(context = this, accentColor = AndesColor(R.color.awesome_color), image = awesomeIconDrawable)
```

Basic Sample XML

```xml
    <com.mercadolibre.android.andesui.thumbnail.AndesThumbnail
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:andesThumbnailImage="@drawable/awesome_icon"
        app:andesThumbnailAccentColor="@color/awesome_color"
        app:andesThumbnailHierarchy="loud"
        app:andesThumbnailType="icon"
         />
```

<br/>

## XML Attributes
| Property | Summary |
| -------- | ------- |
| app:andesThumbnailHierarchy | Determines hierarchy style: **AndesDefault**, **loud**, **quiet** |
| app:andesThumbnailType | Thumbnail design type: **icon**, **image_circle**, **image_square** |
| app:andesThumbnailSize | Thumbnail display size in dp: **size_24**, **size_32**, **size_40**, **size_48**, **size_56**, **size_64**, **size_72**, **size_80** |
| app:andesThumbnailState | Thumbnail status color style: **enabled**, **disabled** |
| app:andesThumbnailImage | Drawable resource to display. |
| app:andesThumbnailAccentColor | Thumbnail main color. |

<br/>

## Constructors
| Summary |
| --- |
| AndesThumbnail(context: Context, attrs: AttributeSet?)  |
| [AndesThumbnail](#andesthumbnailcontext-context-accentcolor-andescolor-hierarchy-andesthumbnailhierarchy-image-drawable-type-andesthumbnailtype-size-andesthumbnailsize-state-andesthumbnailstate)(context: Context, accentColor: [AndesColor](/color/AndesColor.md), hierarchy: [AndesThumbnailHierarchy](#andesthumbnailhierarchy), image: Drawable, type: [AndesThumbnailType](#andesthumbnailtype), size: [AndesThumbnailSize](#andesthumbnailsize), state: [AndesThumbnailState](#andesthumbnailstate)) |

<br/>

##### AndesThumbnail(context: Context, accentColor: AndesColor, hierarchy: AndesThumbnailHierarchy, image: Drawable, type: AndesThumbnailType, size: AndesThumbnailSize, state: AndesThumbnailState)
| Parameter | Description |
| -------- | ------- |
| context | **Context**|
| accentColor | **[AndesColor](/color/AndesColor.md)**: thumbnail main color |
| hierarchy | **[AndesThumbnailHierarchy](#andesthumbnailhierarchy)**: thumbnail hierarchy style. Will affect color palette. |
| image | **Drawable**: thumbnail drawable resource to display. |
| type | **[AndesThumbnailType](#andesthumbnailtype)**: thumbnail design type. |
| size | **[AndesThumbnailSize](#andesthumbnailsize)**: thumbnail display size in dp. |
| state | **[AndesThumbnailState](#andesthumbnailstate)**: thumbnail status. |

<br/>

## Properties
| Property | Summary |
| -------- | ------- |
| accentColor: [AndesColor](/color/AndesColor.md) | **get():** retrieves thumbnail main color. <br/> **set(value: AndesColor):** updates thumbnail main color. |
| hierarchy: [AndesThumbnailHierarchy](#andesthumbnailhierarchy) | **get():** retrieves thumbnail hierarchy style. <br/> **set(value: AndesThumbnailHierarchy):** updates thumbnail hierarchy. |
| image: Drawable | **get():** retrieves thumbnail displayed drawable. <br/> **set(value: Drawable):** updates thumbnail drawable. |
| type: [AndesThumbnailType](#andesthumbnailtype) | **get():** retrieves thumbnail design type. <br/> **set(value: AndesThumbnailType):** updates thumbnail design type. |
| size: [AndesThumbnailSize](#andesthumbnailsize) | **get():** retrieves thumbnail component size. <br/> **set(value: AndesThumbnailSize):** updates thumbnail component size. |
| state: [AndesThumbnailState](#andesthumbnailstate) | **get():** retrieves thumbnail state style. <br/> **set(value: AndesThumbnailState):** updates thumbnail state style. |

<br/>

## Related Classes

### AndesThumbnailHierarchy
Defines the possible color palettes that thumbnail can take.
```kotlin
enum class AndesThumbnailHierarchy
```
| Enum Values | Description |
| ----------- | ----------- |
| DEFAULT | Paints the drawable with black and the background with white color |
| QUIET | Paints the drawable with the main color and the background with a lighter color |
| LOUD | Paints the drawable with white and the background with the main color |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesThumbnailHierarchy | **fromString(value: String)**<br/> Retrieves an AndesThumbnailHierarchy that matches the string value |

<br/>

### AndesThumbnailType
Defines the possible types that thumbnail can take.
```kotlin
enum class AndesThumbnailType
```
| Enum Values | Description |
| ----------- | ----------- |
| ICON | Default rounded image with a tinted icon inside |
| IMAGE_CIRCLE | Rounded image & behaves as [AndesThumbnailHierarchy.DEFAULT](#andesthumbnailhierarchy) |
| IMAGE_SQUARE | Squared image with rounded corners & behaves as [AndesThumbnailHierarchy.DEFAULT](#andesthumbnailhierarchy) |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesThumbnailType | **fromString(value: String)**<br/> Retrieves an AndesThumbnailType that matches the string value |

### AndesThumbnailSize
Defines the possible sizes in dp that thumbnail can take.
```kotlin
enum class AndesThumbnailSize
```
| Enum Values | Description |
| ----------- | ----------- |
| SIZE_24 | 24dp component size |
| SIZE_32 | 32dp component size |
| SIZE_40 | 40dp component size |
| SIZE_48 | 48dp component size |
| SIZE_56 | 56dp component size |
| SIZE_64 | 64dp component size |
| SIZE_72 | 72dp component size |
| SIZE_80 | 80dp component size |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesThumbnailSize | **fromString(value: String)**<br/> Retrieves an AndesThumbnailSize that matches the string value |

<br/>

### AndesThumbnailState
Defines the possible state styles that thumbnail can take.
```kotlin
enum class AndesThumbnailState
```
| Enum Values | Description |
| ----------- | ----------- |
| DISABLED | Thumbnail component will be tinted with gray |
| ENABLED | Thumbnail component  will be tinte according to the color and [hierarchy](#andesthumbnailhierarchy) defined  |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesThumbnailState | **fromString(value: String)**<br/> Retrieves an AndesThumbnailState that matches the string value |

<br/>

## Screenshots
<img src="https://user-images.githubusercontent.com/58984116/111321214-7b560600-8646-11eb-8cf8-bba99b5f4df9.png" width="300">