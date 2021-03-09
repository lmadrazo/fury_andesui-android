# AndesCheckbox

AndesCheckbox allow you to make multiple selections from a list of options, unlike radio buttons that only allow you to choose a single option. 
[See Andes UI component in frontify](https://company-161429.frontify.com/d/kxHCRixezmfK/n-a#/components/checkbox)

```kotlin
class AndesCheckbox : ConstraintLayout
```

Basic Sample Programatically

```kotlin
AndesCheckbox(context)
```
Basic Sample XML

```xml
<com.mercadolibre.android.andesui.checkbox.AndesCheckbox
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
<br/>

## XML Attributes
| Property | Summary |
| -------- | ------- |
| app:andesCheckboxText | Text to display with the checkbox. It must be a string resource. |
| app:andesCheckboxAlign | Set the checkbox position at the left or right of the **andesCheckboxText**. Possible values: **left**, **right** |
| app:andesCheckboxStatus | Set checkbox initial status: **selected**, **unselected**, **undefined** |
| app:andesCheckboxType | Set a type style for the checkbox: **idle**, **error**, **disabled** |

<br/>

## Constructors
| Summary |
| --- |
| AndesCheckbox(context: Context, attrs: AttributeSet?) |
| [AndesCheckbox](#andescheckboxcontext-context-text-string-align-andescheckboxalign-status-andescheckboxstatus-type-andescheckboxtype)(context: Context, text: String, align: [AndesCheckboxAlign](#andescheckboxalign), status: [AndesCheckboxStatus](#andescheckboxstatus), type: [AndesCheckboxType](#andescheckboxtype))|

<br/>

##### AndesCheckbox(context: Context, text: String, align: AndesCheckboxAlign, status: AndesCheckboxStatus, type: AndesCheckboxType)
| Parameter | Description |
| -------- | ------- |
| context | **Context**|
| text | **String**: Checkbox companion text. |
| align | **[AndesCheckboxAlign](#andescheckboxalign)**: Set the checkbox position at the left or right of the text. Default alignment is **LEFT** |
| status | **[AndesCheckboxStatus](#andescheckboxstatus)**: Set checkbox initial status. Default status is **UNSELECTED** |
| type | **[AndesCheckboxType](#andescheckboxtype)**: Set the checkbox type style. Default type is **IDLE** |

<br/>

## Properties
| Property | Summary |
| -------- | ------- |
| text: String? | **get():** retrieves checkbox companion text displayed. <br/> **set(value: String?):** updates component text displayed. |
| align: AndesCheckboxAlign | **get():** retrieves checkbox position. <br/> **set(value: AndesCheckboxAlign):** updates checkbox position at the left or right of the text. |
| status: AndesCheckboxStatus | **get():** retrieves checkbox current status. <br/> **set(value: AndesCheckboxStatus):** updates checkbox status. |
| type: AndesCheckboxType | **get():** retrieves checkbox type. <br/> **set(value: AndesCheckboxType):** updates component type. |

<br/>

## Related Classes

### AndesCheckboxAlign
Defines the possible positions that the checkbox can take.
```kotlin
enum class AndesCheckboxAlign
```
| Enum Values |
| -------- |
| LEFT |
| RIGHT |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesCheckboxAlign | **fromString(value: String)**<br/> Retrieves an AndesCheckboxAlign that matches the string value |

<br/>

### AndesCheckboxStatus
Defines the possible status that the checkbox can take.
```kotlin
enum class AndesCheckboxStatus
```
| Enum Values |
| -------- |
| SELECTED |
| UNSELECTED |
| UNDEFINED |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesCheckboxStatus | **fromString(value: String)**<br/> Retrieves an AndesCheckboxStatus that matches the string value |

<br/>

### AndesCheckboxType
Defines the possible type styles that the checkbox can take.
```kotlin
enum class AndesCheckboxType
```
| Enum Values |
| -------- |
| IDLE |
| DISABLED |
| ERROR |

<br/>

#### Functions
| Return type | Method |
| -------- | ------- |
| AndesCheckboxType | **fromString(value: String)**<br/> Retrieves an AndesCheckboxType that matches the string value |

<br/>
