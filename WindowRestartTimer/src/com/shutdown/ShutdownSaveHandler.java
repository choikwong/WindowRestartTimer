package com.shutdown;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.shutdown.exception.DayNotExistException;
import com.shutdown.exception.TimeFormatNotMatchException;
import com.shutdown.exception.TimeRangeException;

/**
 * A handler is handling the saving performance.
 * 
 * @author Raymond
 * 
 */
public class ShutdownSaveHandler {
    String path;
    private File f = null;

    /**
     * Basic Constructor
     * 
     */
    public ShutdownSaveHandler() {
	path = "C:" + File.separator + "ShutInTime" + File.separator
		+ "saved.xml";
	f = new File(path);
    }

    /**
     * Check is the File isExisted.
     * 
     * @return <br>
     *         true/false
     */
    public boolean isFileExisted() {
	if (f.exists() && !f.isDirectory()) {
	    return true;
	}
	return false;
    }

    /**
     * Does the file is blank.
     * 
     * @return <br>
     *         true/false
     */
    public boolean isBlankFile() {
	BufferedReader reader = null;
	int readSize = -1;
	// create reader instance
	try {
	    reader = new BufferedReader(new FileReader(f));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	// get the size of document
	try {
	    readSize = reader.read();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// if -1 is blank.
	if (readSize == -1)
	    return true;
	else
	    return false;

    }

    /**
     * Create a file in C:// , ShutInTime/task.dtd and ShutInTime/saved.xml are
     * created .
     * 
     * @return <br>
     *         true/false
     */
    public boolean createSavedFile() {
	if (!isFileExisted()) {
	    path = "C:" + File.separator + "ShutInTime";
	    // (use relative path for Unix systems)
	    f = new File(path);
	    f.mkdirs();
	    path = "C:" + File.separator + "ShutInTime" + File.separator
		    + "task.dtd";
	    // (use relative path for Unix systems)
	    f = new File(path);
	    try {
		f.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    initialDTD();
	    path = "C:" + File.separator + "ShutInTime" + File.separator
		    + "saved.xml";
	    // (use relative path for Unix systems)
	    f = new File(path);

	    try {
		f.createNewFile();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return true;
	}
	return false;
    }

    /**
     * Put the data set in to the xml file
     * 
     * @param shutdown_Day
     * <br>
     *            A HashMap<String,boolean> file is getting form ShutdownTask.
     * @param shutdown_Time
     * <br>
     *            A HashMap<String,String> file is getting form ShutdownTask.
     */
    public void saveToFile(Map shutdown_Day, Map shutdown_Time) {
	Document dom;
	Element e = null;

	// instance of a DocumentBuilderFactory
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	try {
	    // use factory to get an instance of document builder
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    // create instance of DOM
	    dom = db.newDocument();

	    // create the root element
	    Element rootEle = dom.createElement("Tasks");

	    // create data elements and place them under root

	    for (Object s : shutdown_Day.keySet()) {
		Element Ele = dom.createElement(s.toString());
		e = dom.createElement("Actived");
		e.appendChild(dom
			.createTextNode(shutdown_Day.get(s).toString()));
		Ele.appendChild(e);
		e = dom.createElement("Time");
		e.appendChild(dom.createTextNode(shutdown_Time.get(s)
			.toString()));
		Ele.appendChild(e);
		rootEle.appendChild(Ele);

	    }

	    dom.appendChild(rootEle);

	    try {
		Transformer tr = TransformerFactory.newInstance()
			.newTransformer();
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.setOutputProperty(OutputKeys.METHOD, "xml");
		tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "task.dtd");
		tr.setOutputProperty(
			"{http://xml.apache.org/xslt}indent-amount", "4");

		// send DOM to file
		tr.transform(new DOMSource(dom), new StreamResult(
			new FileOutputStream(f)));

	    } catch (TransformerException te) {
		System.out.println(te.getMessage());
	    } catch (IOException ioe) {
		System.out.println(ioe.getMessage());
	    }
	} catch (ParserConfigurationException pce) {
	    System.out
		    .println("UsersXML: Error trying to instantiate DocumentBuilder "
			    + pce);
	}
    }

    /**
     * Get data from XML file and generate data sets.
     * @return <br>
     *         Array of Map includes
     *         HashMap<String,boolean>(getAllShutdown_Days) and
     *         HashMap<String,String>(getAllShutdownTime).
     */
    public Map[] readFromFile() {
	ShutdownTask tmST = new ShutdownTask();
	Map[] m = new Map[] { tmST.getAllShutdown_Days(),
		tmST.getAllShutdownTime() };
	Document dom;
	// Make an instance of the DocumentBuilderFactory
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	try {
	    // use the factory to take an instance of the document builder
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    // parse using the builder to get the DOM mapping of the
	    // XML file
	    dom = db.parse(f);

	    for (Object o : tmST.getAllShutdown_Days().keySet()) {
		NodeList nl = dom.getDocumentElement()
			.getElementsByTagName(o.toString()).item(0)
			.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
		    // get an Actived
		    if (nl.item(i).toString().contains("Actived")) {
			try {
			    if (nl.item(i).getTextContent().equals("true")) {

				tmST.setShutdownDay(o.toString(), true);

			    } else {
				tmST.setShutdownDay(o.toString(), false);
			    }
			} catch (DayNotExistException e) {
			    e.printStackTrace();
			}
		    }
		    // get a Time
		    if (nl.item(i).toString().contains("Time")) {
			try {
			    tmST.setShutdownTime(o.toString(), nl.item(i)
				    .getTextContent());
			} catch (DOMException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (DayNotExistException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (TimeFormatNotMatchException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (TimeRangeException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

		    }
		}
	    }

	    m = new Map[] { tmST.getAllShutdown_Days(),
		    tmST.getAllShutdownTime() };
	    return m;

	} catch (ParserConfigurationException pce) {
	    System.out.println(pce.getMessage());
	} catch (SAXException se) {
	    System.out.println(se.getMessage());
	} catch (IOException ioe) {
	    System.err.println(ioe.getMessage());
	}

	return m;
    }

    /**
     * Generating the DTD When file is creating.
     */
    private void initialDTD() {
	BufferedWriter writer = null;
	try {
	    writer = new BufferedWriter(new FileWriter(f));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try {
	    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	    writer.write("<!ELEMENT Tasks (" + ShutdownTask.MONDAY + ","
		    + ShutdownTask.TUESDAY + "," + ShutdownTask.WEDNESDAY + ","
		    + ShutdownTask.THURDAY + "," + ShutdownTask.FRIDAY + ","
		    + ShutdownTask.SATURDAY + "," + ShutdownTask.SUNDAY
		    + ")>\n");
	    writer.write("<!ELEMENT " + ShutdownTask.MONDAY
		    + " (Actived,Time)>\n");
	    writer.write("<!ELEMENT " + ShutdownTask.TUESDAY
		    + " (Actived,Time)>\n");
	    writer.write("<!ELEMENT " + ShutdownTask.WEDNESDAY
		    + " (Actived,Time)>\n");
	    writer.write("<!ELEMENT " + ShutdownTask.THURDAY
		    + " (Actived,Time)>\n");
	    writer.write("<!ELEMENT " + ShutdownTask.FRIDAY
		    + " (Actived,Time)>\n");
	    writer.write("<!ELEMENT " + ShutdownTask.SATURDAY
		    + " (Actived,Time)>\n");
	    writer.write("<!ELEMENT " + ShutdownTask.SUNDAY
		    + " (Actived,Time)>\n");
	    writer.write("<!ELEMENT Actived (#PCDATA)>\n");
	    writer.write("<!ELEMENT Time (#PCDATA)>");
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
