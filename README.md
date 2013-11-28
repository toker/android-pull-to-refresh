# Pull To Refresh Views for Android `Renewal`! v3.0
### This project is a fork of Chris Banes' Android-PullToRefresh project. The project provides flexibility in customization in addition to adding brand new features listed below.

- Easy layout customization - no need to include the library's source code directly into your project. 
- Easily Customizable loading layouts, labels, and icons. 
- Ability to customize and add multiple indicator layouts. 
- Configurable friction and smooth scroll duration. 
- This fork project is __not__ being deprecated. Please send issues or pull requests to me, and I will provide you with feedbacks.

- __Are you are using Pull To Refresh v2.1.x? You'd better [MIGRATE](http://hive.nhncorp.com/weblab/Android-PullToRefresh/post/5) to v3.0 now! __

### Introduction
* * *



![Screenshot](https://github.com/chrisbanes/Android-PullToRefresh/raw/master/header_graphic.png)

This project aims to provide a reusable Pull to Refresh widget for Android. It was originally based on Johan Nilsson's [library](https://github.com/johannilsson/android-pulltorefresh) (mainly for graphics, strings and animations), but these have been replaced since.

## Included Features

 * Supports both Pulling Down from the top, and Pulling Up from the bottom (or even both).
 * Animated Scrolling for all devices.
 * Over Scroll supports for devices on Android v2.3+.
 * Currently works with:
    * **ListView**
    * **ExpandableListView**
    * **GridView**
    * **WebView**
    * **ScrollView**
    * **HorizontalScrollView**
    * **ViewPager**
 * Integrated End of List Listener for use of detecting when the user has scrolled to the bottom.
 * Maven Support.
 * Indicators to show the user when a Pull-to-Refresh is available.
 * Support for **ListFragment**!
 * Lots of [Customization](http://yobi.navercorp.com/weblab/Android-PullToRefresh/post/4) options!

Repository at <http://yobi.navercorp.com:80/weblab/Android-PullToRefresh>.

## Sample Application
Sample application is available on Google Play (the source is in the repository):

[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](http://play.google.com/store/apps/details?id=com.handmark.pulltorefresh.samples)

## Usage
To begin using the library, please see the [Quick Start Guide](http://hive.nhncorp.com/weblab/Android-PullToRefresh/post/1).

### Customization
Our [Customization](http://yobi.navercorp.com/weblab/Android-PullToRefresh/post/4) page contains detailed information on how to change the behaviour and look of the View.

### Pull Up to Refresh
By default this library is set to Pull Down to Refresh, but if you want to allow Pull Up to Refresh then you can do so. You can even set the View to enable both Pull Up and Pull Down using the 'both' setting. See the [Customization](http://yobi.navercorp.com/weblab/Android-PullToRefresh/post/4) page for more information on how to set this.

## Apps
Want to see which Apps are already using Android-PullToRefresh? Have a look [here](https://github.com/chrisbanes/Android-PullToRefresh/wiki/Apps). 

## Changelog
Please see the new [Changelog](http://hive.nhncorp.com/weblab/Android-PullToRefresh/post/2) page to see what's recently changed.

## Pull Requests

I will gladly accept pull requests for fixes and feature enhancements but please do them in the dev branch. The master branch is for the latest stable code,  dev is where I try things out before releasing them as stable. Any pull requests that are against master from now on will be closed asking for you to do another pull against dev.

## Acknowledgments

* [Stefano Dacchille](https://github.com/stefanodacchille)
* [Steve Lhomme](https://github.com/robUx4)
* [Maxim Galkin](https://github.com/mgalkin)
* [Scorcher](https://github.com/Scorcher)


## License

    Copyright 2011, 2012 Chris Banes

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.