package hashtagrandom.games.seppe.stakescape.util;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class XMLParser {

	public static final String FILENAME = "staker.xml";
	public static String PATH;

	private Document doc;
	private Element playerElement;
	private Element rootElement;
	private File file;

	public XMLParser() {

		//	setEnvironementDirectory();
		/*
		if(!this.doesXMLExists()){
			this.createXML();
		} else {
			this.initXML();
			if (!this.readVersionFromXML().equals(MainMenu.XMLVERSION)) {
				this.removeXML();
				this.createXML();
			}
		}
			
	}
	*/
	/*
	 * Get the XML content for futher use in the code
	 */
		/*
	private void initXML(){
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
					
			doc.getDocumentElement().normalize();
			rootElement = (Element) doc.getElementsByTagName("staker").item(0);
			playerElement = (Element) doc.getElementsByTagName("player").item(0);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/*
	 * Create the XML with the basic structure
	 */
		/*
	private void createXML(){
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("staker");
			doc.appendChild(rootElement);

			// player element
			Element playerElement = doc.createElement("player");
			rootElement.appendChild(playerElement);
			
			Element version = doc.createElement("version");
		//	version.appendChild(doc.createTextNode(MainMenu.XMLVERSION));
			rootElement.appendChild(version);

			// money elements
			Element money = doc.createElement("money");
			money.appendChild(doc.createTextNode("10000"));
			playerElement.appendChild(money);

			Element secret = doc.createElement("secret");
		//	secret.appendChild(doc.createTextNode(encryptHash("10000")));
			playerElement.appendChild(secret);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(PATH + FILENAME));

			transformer.transform(source, result);

			System.out.println("File saved!");
			
			//After the XML is created, get the content of the XML
			this.initXML();

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}
	*/
	/*
	 * Remove the xml
	 * If there is a new structure available for the xml, it is necessary to remove it and recreate it.
	 * There is an XMLVERSION implemented to check if there is a new structure available
	 */
		/*
	private void removeXML(){
		file = new File(PATH + FILENAME);
		file.delete();
	}
	*/
	/*
	 * Check if the XML file exists on the given path
	 */
		/*
	private boolean doesXMLExists(){
		boolean exists = false;
		
		file = new File(PATH + FILENAME);
		if(file.exists() && !file.isDirectory()){
			exists = true;
		}
		
		return exists;
	}
	*/
	/*
	 * Save the manipulated DOM to the file
	 */
		/*
	private void saveToXML(){
		try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(file)));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (FileNotFoundException fnfe) {
        	fnfe.printStackTrace();
		}
	}
	*/
	
/*	public User readUserFromXML(User user){

		String money = playerElement.getElementsByTagName("money").item(0).getTextContent();
		String secret = playerElement.getElementsByTagName("secret").item(0).getTextContent();

		if (encryptHash(money).equals(secret)) {
			user.setMoney(Integer.parseInt(money));
		} else {
			user.setMoney(0);
		}

		return user;

	}
	
	public String readVersionFromXML(){
		String verion = rootElement.getElementsByTagName("version").item(0).getTextContent();
		return verion;
	}
	
	public void writeUsertoXML(User user){
		
		Node moneyNode = playerElement.getElementsByTagName("money").item(0);
		Node secretNode = playerElement.getElementsByTagName("secret").item(0);
		
		moneyNode.setTextContent(Integer.toString(user.getMoney()));
		secretNode.setTextContent(encryptHash(Integer.toString(user.getMoney())));

		playerElement.appendChild(moneyNode);
		playerElement.appendChild(secretNode);

		doc.getDocumentElement().appendChild(playerElement);

		saveToXML();
	}

	
	private static void setEnvironementDirectory()
	{
		String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN")) {
			PATH = System.getenv("APPDATA") + "\\";
		}else {
			PATH = System.getProperty("user.home") + "/";
		}
	}

	private static String encryptHash(String money){

		String strToHash = money + User.HASH_SECRET;
		String encryptedString = "";

		try{
			encryptedString = Util.hashString(strToHash);
		}catch (NoSuchAlgorithmException nse) {
			nse.printStackTrace();
		}

		return encryptedString;
	}
	*/
	}
}
