package org.wicketstuff.extjs.tree;

import java.util.Iterator;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;

public abstract class ExtTreePanel extends MarkupContainer {

	private static final long serialVersionUID = 1L;
	private ExtTreeDataLinkBehavior data;
	private String root;
	
	private Config config ;
	
	{
		config = new Config();
		config.set("width", 220);
		config.set("height", 260);
	}
	
	public ExtTreePanel(String id, IModel model, String root) {
		super(id, model);
		init(root);
	}

	public ExtTreePanel(String id, String root) {
		super(id);
		init(root);
	}

	private void init(String root) {
		add( (IBehavior)(data = new ExtTreeDataLinkBehavior(this)) );
		add(new ExtTreePanelBehavior(config));
		this.root = root;
	}
	
	public ExtTreePanel setWidth( int width ) { 
		config.set("width", width );
		return this;
	}
	
	public ExtTreePanel setHeight( int height ) { 
		config.set("height", height );
		return this;
	}
	
	public ExtTreePanel setAutoScroll( boolean autoScroll ) { 
		config.set("autoScroll", autoScroll );
		return this;
	}
	
	public ExtTreePanel setBorder( boolean  border  ) { 
		config.set("border",  border  );
		return this;
	}
	
	public ExtTreePanel setBodyBorder( boolean bodyBorder ) { 
		config.set("bodyBorder", bodyBorder );
		return this;
	}
	
	public ExtTreePanel setAutoHeight( boolean autoHeight ) { 
		config.set("autoHeight",  autoHeight  );
		return this;
	}
	
	public ExtTreePanel setAnimate( boolean  animate ) { 
		config.set("animate", animate );
		return this;
	}
	
	public ExtTreePanel setAnimCollapse( boolean animCollapse ) { 
		config.set("animCollapse", animCollapse );
		return this;
	}
	
	public ExtTreePanel setTitle( String title ) { 
		config.set("title", title );
		return this;
	}
	
	public ExtTreePanel setRootVisible( boolean rootVisible ) { 
		config.set("rootVisible", rootVisible );
		return this;
	}
	
	public ExtTreePanel setUseArrows( boolean useArrows ) { 
		config.set("useArrows", useArrows );
		return this;
	}
	
	public abstract Iterator<TreeNode> iterator(String id);
	
	public class ExtTreePanelBehavior extends ExtComponentBehavior {

		private static final long serialVersionUID = 1L;
		
		public ExtTreePanelBehavior( ) { 
			this(new Config());
		}
		
		public ExtTreePanelBehavior( Config config ) { 
			super("Ext.tree.TreePanel", config);
		}
		
		@Override
		protected String getApplyMethod() { 
			return "renderTo";
		}
		
		@Override
		protected CharSequence onExtScript(Config config) { 
			StringBuilder result = new StringBuilder();

			ExtAsyncTreeNode rootNode = new ExtAsyncTreeNode(root,ExtAsyncTreeNode.ROOT_ID);
			result.append( rootNode.newInstance("root") );
			
			ExtTreeLoader loader = new ExtTreeLoader(data.getCallbackUrl().toString());
			result.append( loader.newInstance("loader") );
			
			config.set("root", rootNode );
			config.set("loader", loader );
			result.append( create(config).newInstance("tree") );
			
			return result;
		}	
	}
}
