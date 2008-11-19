Ext.form.AutoComplete = function(config) {
	this.sep = config.sep;
	Ext.form.AutoComplete.superclass.constructor.call(this, config);
};

Ext.extend(Ext.form.AutoComplete,  Ext.form.ComboBox, {
	getPosition: function() {
		if (document.selection) { // IE
			var r = document.selection.createRange();
			var d = r.duplicate();
			d.moveToElementText(this.el.dom);
			d.setEndPoint( 'EndToEnd', r );
			return d.text.length;
		} else  {
			return this.el.dom.selectionEnd;
		}
	},
	
	getActiveRange: function() {
		var s = this.sep;
		var p = this.getPosition();
		var v = this.getRawValue();
		var left = p
		while (left > 0 && v.charAt(left) != s)
			--left;
		if (left > 0) left++;
		return {left: left, right: p};	
	},
	
	getActiveEntry: function() {
		var r = this.getActiveRange();
		return this.getRawValue().substring(r.left, r.right).replace(/^\s+|\s+$/g, '');
	},
	
	replaceActiveEntry: function(value) {
		var r = this.getActiveRange();
		var v = this.getRawValue();
		var pad = (this.sep == ' ' ? '' : ' ');
		this.setValue(v.substring(0, r.left) + pad + value + this.sep + pad + v.substring(r.right));
		var p = r.left + value.length + 2 + pad.length;
		this.selectText.defer(200, this, [p,p]);
	},
	
	// private
	onSelect : function(record, index){
		if(this.fireEvent('beforeselect', this, record, index) !== false){
			var value = record.data[this.valueField || this.displayField];
			if (this.sep) {
				this.replaceActiveEntry(value);
			} else {
				this.setValue(value);
			}
			this.collapse();
			this.fireEvent('select', this, record, index);
		}
	},
	
	// private
	initQuery : function(){
		this.doQuery(this.sep ?  this.getActiveEntry() : this.getRawValue());
	}
});