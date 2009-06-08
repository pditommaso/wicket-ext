/*
 *  Copyright 2008 Wicket-Ext
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
* @class Ext.form.ComboBoxMulti
* @extends Ext.form.ComboBox
* Adds freeform multiselect and duplicate entry prevention to the standard combobox
* @constructor
* Create a new ComboBoxMulti.
* @param {Object} config Configuration options
*/
Ext.form.ComboBoxMulti = function(config){
/**
* @cfg {String} sep is used to separate text entries
*/
/**
* @cfg {Boolean} preventDuplicates indicates whether repeated selections of the same option will generate extra entries
*/
Ext.apply(config);

// this option will interfere will expected operation
this.typeAhead = false;
// these options customize behavior
this.minChars = 2;
this.hideTrigger = true;
this.defaultAutoCreate = {
autocomplete: "off"
};

Ext.form.ComboBoxMulti.superclass.constructor.call (this, config);
};

Ext.form.ComboBoxMulti = Ext.extend(Ext.form.ComboBoxMulti, Ext.form.ComboBox, {
getPosition: function(){
if (document.selection) { // IE
var r = document.selection.createRange();
var d = r.duplicate();
d.moveToElementText(this.el.dom);
d.setEndPoint('EndToEnd', r);
return d.text.length;
}
else {
return this.el.dom.selectionEnd;
}
},

getActiveRange: function(){
var s = this.sep;
var p = this.getPosition();
var v = this.getRawValue();
var left = p;
while (left > 0 && v.charAt(left) != s) {
--left;
}
if (left > 0) {
left++;
}
return {
left: left,
right: p
};
},

getActiveEntry: function(){
var r = this.getActiveRange();
return this.getRawValue().substring(r.left, r.right).replace(/^\s+|\s+$/g, '');
},

replaceActiveEntry: function(value){
var r = this.getActiveRange();
var v = this.getRawValue();
if (this.preventDuplicates && v.indexOf(value) >= 0) {
return;
}
var pad = (this.sep == ' ' ? '' : ' ');
this.setValue(v.substring(0, r.left) + (r.left > 0 ? pad : '') + value + this.sep + pad + v.substring(r.right));
var p = r.left + value.length + 2 + pad.length;
this.selectText.defer(200, this, [p, p]);
},

onSelect: function(record, index){
if (this.fireEvent('beforeselect', this, record, index) !== false) {
var value = record.data[this.valueField || this.displayField];
if (this.sep) {
this.replaceActiveEntry(value);
}
else {
this.setValue(value);
}
this.collapse();
this.fireEvent('select', this, record, index);
}
},

initQuery: function(){
this.doQuery(this.sep ? this.getActiveEntry() : this.getRawValue());
}
});
Ext.reg('comboboxmulti',Ext.form.ComboBoxMulti); 
