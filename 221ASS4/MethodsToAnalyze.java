<!DOCTYPE html>
<html lang="en">
<head>
<title>MethodsToAnalyze.java</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <style type="text/css" media="screen">
        html, body {
            height: 100%;
        }
        .container {
            position: relative;
            height: 100%;
                    }
        #editor { 
            position: relative !important;
            border: 1px solid lightgray;
            margin: auto;
            height: 200px;
            width: 100%;
            font-size: 12pt;
        }
        #header {
            margin-bottom: 10px;
            margin-top: 10px;
        }
        #header .title h1 {
            float:left;
            height: 50px;
            margin: 0px;
            margin-right: 20px;
        }
        #header .info {
            float:left;
            height: 50px;
        }
        #header .toolbox {
            height: 50px;
            float: right;
        }
        .toolbox .checkbox {
            margin: 0px;
            padding: 0px;
        }
        #footer p{
            margin-top: 25px;
            margin-bottom: 15px;
            text-align: center;
            font-size: 12pt;
        }
        
    /* style applied to line numbers in the gutter */
    .ace_gutter-cell {
        /* Custom color for the line number color */
        color: #999;
        /* If you change this font size for line numbers, you will need to set the line-height
           to the height of the row. */
        /* font-size: 10pt;    */
        /* line-height: 22px;  */ /* 22px is the height when using 14pt 12pt */
    }
    </style>
</head>
<body>
<div class="container">
    <div id="header">
		<div class="title"><h1>MethodsToAnalyze.java</h1></div>
		<div class="info">
        <a href="?raw">Download/View Raw File</a><br>
		Filetype: <span id="filetype">Unknown</span>
		</div>
        <div class="toolbox">
            <div class="checkbox">
                <label for="show_invisibles">Show hidden characters
                <input type="checkbox"
                       onchange="editor.setShowInvisibles(this.checked);"
                       id="show_invisibles" ></input>
                </label>
                <br>
                <label for="wrap_lines">Wrap long lines
                <input type="checkbox"
                       onchange="editor.getSession().setUseWrapMode(this.checked);"
                       id="wrap_lines" CHECKED></input>
                </label>
            </div>
        </div>
    </div>
    <div class="row content">
	        <pre id="editor">/**
 * A collection of methods that work with arrays of ints.
 * 
 * @author mvail
 */
public class MethodsToAnalyze {

	/**
	 * Return index where value is found in array or -1 if not found.
	 * @param array ints where value may be found
	 * @param value int that may be in array
	 * @return index where value is found or -1 if not found
	 */
	public static int find(int[] array, int value) {
		for (int i = 0; i &lt; array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Replace all occurrences of oldValue with newValue in array.
	 * @param array ints where oldValue may be found
	 * @param oldValue value to replace
	 * @param newValue new value
	 */
	public static void replaceAll(int[] array, int oldValue, int newValue) {
		int index = find(array, oldValue);
		while (index &gt; -1) {
			array[index] = newValue;
			index = find(array, oldValue);
		}
	}
	
	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	public static void sortIt(int[] array) {
		for (int next = 1; next &lt; array.length; next++) {
			int value = array[next];
			int index = next;
			while (index &gt; 0 &amp;&amp; value &lt; array[index - 1]) {
				array[index] = array[index - 1];
				index--;
			}
			array[index] = value;
		}
	}
}
</pre>
	    </div>
    <div id="footer">
    <p>
        Syntax highlighting provided by <a href="http://ace.c9.io">Ace</a>.
    </p>
    </div>
</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/ace/1.1.3/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/ace/1.1.3/ext-modelist.js" type="text/javascript" charset="utf-8"></script>
<script>
    editor = ace.edit("editor");
    editor.setTheme("ace/theme/chrome");
    editor.getSession().setUseWrapMode(document.getElementById("wrap_lines").checked);
    editor.setReadOnly(true);
    // Make the editor as big as the file to prevent scrolling(or 50 lines min)
    editor.setOption("maxLines", Infinity);
    editor.setOption("minLines", 50);
    editor.setShowInvisibles(document.getElementById("show_invisibles").checked);
    // Autodetect mode based on file name
    var modelist = ace.require('ace/ext/modelist');
    var mode = modelist.getModeForPath("MethodsToAnalyze.java");
    editor.getSession().setMode(mode.mode);
	document.getElementById("filetype").innerHTML = mode.caption;
</script>
</body>
</html>
