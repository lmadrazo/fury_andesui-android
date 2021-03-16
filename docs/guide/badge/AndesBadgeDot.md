# AndesBadgeDot

AndesBadgeDot is a small unit of information that allow you to indicate the status of an item or differentiate several similar items from each other.  
[See Andes UI component in frontify](https://company-161429.frontify.com/d/kxHCRixezmfK/n-a#/components/badge/dot)

```kotlin
class AndesBadgeDot : FrameLayout
```

Basic Sample Programatically

```kotlin
AndesBadgeDot(context)
```
Basic Sample XML

```xml
<com.mercadolibre.android.andesui.badge.AndesBadgeDot
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
<br/>

## XML Attributes
| Property | Summary |
| -------- | ------- |
| app:andesBadgeDotType | Set a color palette for the badge: **neutral**, **highlight**, **success**, **warning**, **error** |

<br/>

## Constructors
| Summary |
| --- |
| AndesBadgeDot(context: Context, attrs: AttributeSet?) |
| [AndesBadgeDot](#andesbadgedotcontext-context-type-andesbadgetype)(context: Context, type: [AndesBadgeType](#andesbadgetype))|

<br/>

##### AndesBadgeDot(context: Context, type: AndesBadgeType)
| Parameter | Description |
| -------- | ------- |
| context | **Context**|
| type | **[AndesBadgeType](#andesbadgetype)**: this value can be null. Default type is **NEUTRAL** |

<br/>

## Properties
| Property | Summary |
| -------- | ------- |
| type: [AndesBadgeType](#andesbadgetype) | **get():** retrieves badge color palette <br/> **set(value: [AndesBadgeType](#andesbadgetype)):** updates badge color palette |

<br/>

## Related Classes

### AndesBadgeType
Defines the possible styles [AndesBadgeDot](#andesbadgedot) can take.
```kotlin
enum class AndesBadgeType
```
| Enum Values | Description |
| --------- | ------------- |
| NEUTRAL | Gives a gray color style to the component |
| HIGHLIGHT | Gives a blue color style to the component |
| SUCCESS | Gives a green color style to the component |
| WARNING | Gives an orange color style to the component |
| ERROR | Gives a red color style to the component |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesBadgeType | **fromString(value: String)**<br/> Retrieves an AndesBadgeType that matches the string value |

<br/>

## Screenshots
<img src="https://user-images.githubusercontent.com/58984116/111320619-efdc7500-8645-11eb-99f8-bbd474709ca8.png" width="300">