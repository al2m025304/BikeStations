# Obtain the API key

1. Open the debug version of the google_maps_api.xml file.
2. In the file, look for a comment with a long URL. The URL's parameters include specific
   information about the app.
3. Copy and paste the URL into a browser.
4. Follow the prompts to create a project in the Google API Console. Because of the parameters in
   the provided URL, the API Console knows to automatically enable the Google Maps Android API.
5. Create an API key and click Restrict Key to restrict the key's use to Android apps. The generated
   API key should start with AIza.
6. In the google_maps_api.xml file, paste the key into the google_maps_key string where it says
   YOUR_KEY_HERE.