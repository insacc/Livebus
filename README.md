# Livebus

#### Event bus using android architecture components

LiveBus is an event bus for Android which uses `LiveData` component of the android architecture library.

- This library
      
     - Makes it easier to communicate between fragments/activities and view models. 
      
     - Helps you to decouple different parts of your application
      
     - is easy to implement and maintain. There is no additional knowledge needed if you know the android architecture components
     
     - uses the publish/subscribe pattern
     
     
#### Add LiveBus to your project
You can add the library through maven
```
<dependency>
  <groupId>org.insac.core</groupId>
  <artifactId>livebus</artifactId>
  <version>0.2.2</version>
  <type>pom</type>
</dependency>
``` 
or through gradle

```
implementation 'org.insac.core:livebus:0.2.2
```

#### Usage

   ##### Publishing an event
   There are there different event type on LiveBus: `LiveEvent`, `SingleLiveEvent`, `StickyLiveEvent`
   
   `LiveEvent` will broadcast the event value passed to it until it doesn't have any observer oberserving the values.
   
   `SingleLiveEvent` will send the event value passed to it, to only one of its observer and then the event value will be set to null.
   
   `StickyLiveEvent` will broadcast the event value passed to it until it is removed from the bus by explicitly calling `removeEvent(tag)`.


Every event on LiveBus is identified by a unique tag. The same tag needs to be used to publish an event and subscribe to an event. Every subscribe/ publish function calls will create an empty event `LiveData` on the bus if it hasn't been created already.

- `postSingleEvent(tag: String, eventValue: T)` : Use this function if you want to publish a `SingleLiveEvent`.

- `postLiveEvent(tag: String, eventValue: T)` : Use this function to publish a `LiveEvent`.

- `postStickyEvent(tag: String, eventValue: T) ` : Use this function to publish a `StickyEvent`.

##### Subscribing to an Event

Subscribing to an event is very similar to subscribing to a `LiveData` on a `Activity/Fragment/ViewModel`. Pass the tag of the event you want to subscribe to the function depending on the type of the event you want to subscribe and call `observe(...)` function on the returned `LiveData` to process the event.

##### Subscribing to LiveEvent

```
LiveBus.getInstance().subscribeLiveEvent("EVENT_TAG", EVENT_VALUE_CLASS_TYPE)
                .observe(this, Observer {
                    it?.let {
                        // Process event
                    }
                })
                
                

```

##### Subscribing to SingleLiveEvent
```LiveBus.getInstance().subscribeSingleLiveEvent("EVENT_TAG", EVENT_VALUE_CLASS_TYPE)
                .observe(this, Observer {
                    it?.let {
                        // Process event
                    }
                })
                
                
```

##### Subscribing to StickyLiveEvent
```LiveBus.getInstance().subscribeStickyLiveEvent("EVENT_TAG", EVENT_VALUE_CLASS_TYPE)
                .observe(this, Observer {
                    it?.let {
                        // Process event
                    }
                })
                
                
```

###### As the object returned by these functions are `LiveData` objects, no unsubscribe calls are needed, since it'll be automatically handled by the android OS.


#### Removing an event from the bus
```
LiveBus.getInstance().removeEvent("EVENT_TAG")

```
