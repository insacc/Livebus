# Livebus

#### Event bus using android architecture components

LiveBus is an event bus for Android which uses `LiveData` component of the android architecture library to easily manage the lifecycles.

- This library
      
     - makes it easier to communicate between fragments/activities and view models. 
      
     - helps you to decouple different parts of your application
      
     - is easy to implement and maintain. There is no additional knowledge needed if you know the android architecture components
     
     - uses the publish/subscribe pattern
     
     - is completely written in Kotlin
     
     
#### Add LiveBus to your project
You can add the library through maven

```xml
<dependency>
  <groupId>org.insac.core</groupId>
  <artifactId>livebus</artifactId>
  <version>0.3.1</version>
  <type>pom</type>
</dependency>
``` 
or through gradle

```
implementation 'org.insac.core:livebus:0.3.1'
```

#### Usage

   ##### Publishing an event
   There are there different event type on LiveBus: `LiveEvent`, `SingleLiveEvent`, `StickyLiveEvent`
   
   `LiveEvent` will broadcast the event value passed to it until it doesn't have any observer oberserving the values. Note that this type of event will get unsubscribed and removed from the bus whenever the user closes the app/ turn off the screen, use `StickyLiveEvent` if you want to keep the subscription to survive app lifecycles.
   
   `SingleLiveEvent` will send the event value passed to it, to only one of its observer and then the event value will be set to null.
   
   `StickyLiveEvent` will broadcast the event value passed to it until it is removed from the bus by explicitly calling `removeEvent(tag)`.


Every event on LiveBus is identified by an unique tag. The same tag needs to be used to publish an event and subscribe to an event. Every subscribe/ publish function calls will create an empty event `LiveData` on the bus if it hasn't been created already.

- `setSingleLiveEventValue(tag: String, eventValue: T)` : Use this function if you want to publish a `SingleLiveEvent`from the UI thread.


- `postSingleLiveEventValue(tag: String, eventValue: T)` : Use this function if you want to publish a `SingleLiveEvent`from a background thread


- `setLiveEventValue(tag: String, eventValue: T)` : Use this function to publish a `LiveEvent` from the UI thread.


- `postLiveEventValue(tag: String, eventValue: T)` : Use this function to publish a `LiveEvent` from a background thread.


- `setStickyLiveEventValue(tag: String, eventValue: T) ` : Use this function to publish a `StickyEvent` from the UI thread.


- `postStickyLiveEventValue(tag: String, eventValue: T) ` : Use this function to publish a `StickyEvent` from a background thread.


##### Subscribing to an Event

Subscribing to an event is very similar to subscribing to a `LiveData` on a `Activity/Fragment/ViewModel`. Pass the tag of the event you want to subscribe to the function depending on the type of the event you want to subscribe and call `observe(...)` function on the returned `LiveData` to process the event.

##### Subscribing to LiveEvent

```kotlin
LiveBus.getInstance().subscribeLiveEvent("EVENT_TAG", EVENT_VALUE_CLASS_TYPE)
                .observe(this, Observer {
                    it?.let {
                        // Process event
                    }
                })
```

##### Subscribing to SingleLiveEvent
```kotlin
LiveBus.getInstance().subscribeSingleLiveEvent("EVENT_TAG", EVENT_VALUE_CLASS_TYPE)
                .observe(this, Observer {
                    it?.let {
                        // Process event
                    }
                })
```

##### Subscribing to StickyLiveEvent
```kotlin
LiveBus.getInstance().subscribeStickyLiveEvent("EVENT_TAG", EVENT_VALUE_CLASS_TYPE)
                .observe(this, Observer {
                    it?.let {
                        // Process event
                    }
                })
```

###### As the object returned by these functions are `LiveData` objects, no unsubscribe calls are needed, since it'll be automatically handled by the android OS.


#### Removing an event from the bus
```kotlin
LiveBus.getInstance().removeEvent("EVENT_TAG")
```


#### License
```

Copyright 2018 Cevat Can Undeger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
