// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
'use strict';

function setAlarm(event) {
    let minutes = parseFloat(event.target.value);
    chrome.browserAction.setBadgeText({text: 'YES'});
    chrome.browserAction.setBadgeBackgroundColor({color: '#00aa6a'});
    chrome.alarms.create({delayInMinutes: minutes});
    chrome.storage.sync.set({minutes: minutes});
    window.close();
}

function clearAlarm() {
    chrome.browserAction.setBadgeText({text: 'NO'});
    chrome.browserAction.setBadgeBackgroundColor({color: '#f83e37'});
    chrome.alarms.clearAll();
    window.close();
}

//An Alarm delay of less than the minimum 1 minute will fire
// in approximately 1 minute incriments if released
document.getElementById('goodToUse').addEventListener('click', setAlarm);
document.getElementById('badToUse').addEventListener('click', clearAlarm);
