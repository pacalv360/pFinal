package asr.proyectoFinal.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;



import com.google.gson.JsonObject;
import com.ibm.cloud.sdk.core.http.RequestBuilder;
import com.ibm.cloud.sdk.core.http.ResponseConverter;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.ConfigBasedAuthenticatorFactory;
import com.ibm.cloud.sdk.core.service.BaseService;
import com.ibm.cloud.sdk.core.util.RequestUtils;
import com.ibm.cloud.sdk.core.util.ResponseConverterUtils;
import com.ibm.watson.common.SdkCommon;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneChatOptions;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.tone_analyzer.v3.model.UtteranceAnalyses;
import java.util.Map;
import java.util.Map.Entry;



public class ToneAnalyzer extends BaseService {

	  public static final String DEFAULT_SERVICE_NAME = "tone_analyzer";

	  public static final String DEFAULT_SERVICE_URL =
	      "https://api.us-south.tone-analyzer.watson.cloud.ibm.com/instances/59cc60d1-6f93-4b8b-8700-c128c5b5a26b";

	  
	  
	  
	  
	  
	  	/*
	    Authenticator authenticator = new IamAuthenticator("NOvhNn4v5sTeUCPlp78HUjFSXgVvm2Rtw_eDjksdlQVm");
		ToneAnalyzer service = new ToneAnalyzer("2017-09-21", authenticator);
				
		String text =
		  "I know the times are difficult! Our sales have been "
		      + "disappointing for the past three quarters for our data analytics "
		      + "product suite. We have a competitive data analytics product "
		      + "suite in the industry. But we need to do our job selling it! "
		      + "We need to acknowledge and fix our sales challenges. "
		      + "We can’t blame the economy for our lack of execution! "
		      + "We are missing critical sales opportunities. "
		      + "Our product is in no way inferior to the competitor products. "
		      + "Our clients are hungry for analytical tools to improve their "
		      + "business outcomes. Economy has nothing to do with it.";

		// Call the service and get the tone
		ToneOptions toneOptions = new ToneOptions.Builder()
		  .text(text)
		  .build();

		ToneAnalysis tone = service.tone(toneOptions).execute().getResult();
		//out.println(tone);
		*/
		
		
		
		
	  
	  private String version;

	  /**
	   * Constructs an instance of the `ToneAnalyzer` client. The default service name is used to
	   * configure the client instance.
	   *
	   * @param version Release date of the version of the API you want to use. Specify dates in
	   *     YYYY-MM-DD format. The current version is `2017-09-21`.
	   */
	  public ToneAnalyzer(String version) {
	    this(
	        version,
	        DEFAULT_SERVICE_NAME,
	        ConfigBasedAuthenticatorFactory.getAuthenticator(DEFAULT_SERVICE_NAME));
	  }

	  /**
	   * Constructs an instance of the `ToneAnalyzer` client. The default service name and specified
	   * authenticator are used to configure the client instance.
	   *
	   * @param version Release date of the version of the API you want to use. Specify dates in
	   *     YYYY-MM-DD format. The current version is `2017-09-21`.
	   * @param authenticator the {@link Authenticator} instance to be configured for this client
	   */
	  public ToneAnalyzer(String version, Authenticator authenticator) {
	    this(version, DEFAULT_SERVICE_NAME, authenticator);
	  }

	  /**
	   * Constructs an instance of the `ToneAnalyzer` client. The specified service name is used to
	   * configure the client instance.
	   *
	   * @param version Release date of the version of the API you want to use. Specify dates in
	   *     YYYY-MM-DD format. The current version is `2017-09-21`.
	   * @param serviceName the service name to be used when configuring the client instance
	   */
	  public ToneAnalyzer(String version, String serviceName) {
	    this(version, serviceName, ConfigBasedAuthenticatorFactory.getAuthenticator(serviceName));
	  }

	  /**
	   * Constructs an instance of the `ToneAnalyzer` client. The specified service name and
	   * authenticator are used to configure the client instance.
	   *
	   * @param version Release date of the version of the API you want to use. Specify dates in
	   *     YYYY-MM-DD format. The current version is `2017-09-21`.
	   * @param serviceName the service name to be used when configuring the client instance
	   * @param authenticator the {@link Authenticator} instance to be configured for this client
	   */
	  public ToneAnalyzer(String version, String serviceName, Authenticator authenticator) {
	    super(serviceName, authenticator);
	    setServiceUrl(DEFAULT_SERVICE_URL);
	    setVersion(version);
	    this.configureService(serviceName);
	  }

	  /**
	   * Gets the version.
	   *
	   * <p>Release date of the version of the API you want to use. Specify dates in YYYY-MM-DD format.
	   * The current version is `2017-09-21`.
	   *
	   * @return the version
	   */
	  public String getVersion() {
	    return this.version;
	  }

	  /**
	   * Sets the version.
	   *
	   * @param version the new version
	   */
	  public void setVersion(final String version) {
	    com.ibm.cloud.sdk.core.util.Validator.notEmpty(version, "version cannot be empty.");
	    this.version = version;
	  }

	  /**
	   * Analyze general tone.
	   *
	   * <p>Use the general-purpose endpoint to analyze the tone of your input content. The service
	   * analyzes the content for emotional and language tones. The method always analyzes the tone of
	   * the full document; by default, it also analyzes the tone of each individual sentence of the
	   * content.
	   *
	   * <p>You can submit no more than 128 KB of total input content and no more than 1000 individual
	   * sentences in JSON, plain text, or HTML format. The service analyzes the first 1000 sentences
	   * for document-level analysis and only the first 100 sentences for sentence-level analysis.
	   *
	   * <p>Per the JSON specification, the default character encoding for JSON content is effectively
	   * always UTF-8; per the HTTP specification, the default encoding for plain text and HTML is
	   * ISO-8859-1 (effectively, the ASCII character set). When specifying a content type of plain text
	   * or HTML, include the `charset` parameter to indicate the character encoding of the input text;
	   * for example: `Content-Type: text/plain;charset=utf-8`. For `text/html`, the service removes
	   * HTML tags and analyzes only the textual content.
	   *
	   * <p>**See also:** [Using the general-purpose
	   * endpoint](https://cloud.ibm.com/docs/tone-analyzer?topic=tone-analyzer-utgpe#utgpe).
	   *
	   * @param toneOptions the {@link ToneOptions} containing the options for the call
	   * @return a {@link ServiceCall} with a result of type {@link ToneAnalysis}
	   */
	  public ServiceCall<ToneAnalysis> tone(ToneOptions toneOptions) {
	    com.ibm.cloud.sdk.core.util.Validator.notNull(toneOptions, "toneOptions cannot be null");
	    RequestBuilder builder =
	        RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/tone"));
	    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("tone_analyzer", "v3", "tone");
	    for (Entry<String, String> header : sdkHeaders.entrySet()) {
	      builder.header(header.getKey(), header.getValue());
	    }
	    builder.header("Accept", "application/json");
	    if (toneOptions.contentType() != null) {
	      builder.header("Content-Type", toneOptions.contentType());
	    }
	    if (toneOptions.contentLanguage() != null) {
	      builder.header("Content-Language", toneOptions.contentLanguage());
	    }
	    if (toneOptions.acceptLanguage() != null) {
	      builder.header("Accept-Language", toneOptions.acceptLanguage());
	    }
	    builder.query("version", String.valueOf(this.version));
	    if (toneOptions.sentences() != null) {
	      builder.query("sentences", String.valueOf(toneOptions.sentences()));
	    }
	    if (toneOptions.tones() != null) {
	      builder.query("tones", RequestUtils.join(toneOptions.tones(), ","));
	    }
	    builder.bodyContent(
	        toneOptions.contentType(), toneOptions.toneInput(), null, toneOptions.body());
	    ResponseConverter<ToneAnalysis> responseConverter =
	        ResponseConverterUtils.getValue(
	            new com.google.gson.reflect.TypeToken<ToneAnalysis>() {}.getType());
	    return createServiceCall(builder.build(), responseConverter);
	  }

	  /**
	   * Analyze customer-engagement tone.
	   *
	   * <p>Use the customer-engagement endpoint to analyze the tone of customer service and customer
	   * support conversations. For each utterance of a conversation, the method reports the most
	   * prevalent subset of the following seven tones: sad, frustrated, satisfied, excited, polite,
	   * impolite, and sympathetic.
	   *
	   * <p>If you submit more than 50 utterances, the service returns a warning for the overall content
	   * and analyzes only the first 50 utterances. If you submit a single utterance that contains more
	   * than 500 characters, the service returns an error for that utterance and does not analyze the
	   * utterance. The request fails if all utterances have more than 500 characters. Per the JSON
	   * specification, the default character encoding for JSON content is effectively always UTF-8.
	   *
	   * <p>**See also:** [Using the customer-engagement
	   * endpoint](https://cloud.ibm.com/docs/tone-analyzer?topic=tone-analyzer-utco#utco).
	   *
	   * @param toneChatOptions the {@link ToneChatOptions} containing the options for the call
	   * @return a {@link ServiceCall} with a result of type {@link UtteranceAnalyses}
	   */
	  public ServiceCall<UtteranceAnalyses> toneChat(ToneChatOptions toneChatOptions) {
	    com.ibm.cloud.sdk.core.util.Validator.notNull(
	        toneChatOptions, "toneChatOptions cannot be null");
	    RequestBuilder builder =
	        RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/v3/tone_chat"));
	    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("tone_analyzer", "v3", "toneChat");
	    for (Entry<String, String> header : sdkHeaders.entrySet()) {
	      builder.header(header.getKey(), header.getValue());
	    }
	    builder.header("Accept", "application/json");
	    if (toneChatOptions.contentLanguage() != null) {
	      builder.header("Content-Language", toneChatOptions.contentLanguage());
	    }
	    if (toneChatOptions.acceptLanguage() != null) {
	      builder.header("Accept-Language", toneChatOptions.acceptLanguage());
	    }
	    builder.query("version", String.valueOf(this.version));
	    final JsonObject contentJson = new JsonObject();
	    contentJson.add(
	        "utterances",
	        com.ibm.cloud.sdk.core.util.GsonSingleton.getGson()
	            .toJsonTree(toneChatOptions.utterances()));
	    builder.bodyJson(contentJson);
	    ResponseConverter<UtteranceAnalyses> responseConverter =
	        ResponseConverterUtils.getValue(
	            new com.google.gson.reflect.TypeToken<UtteranceAnalyses>() {}.getType());
	    return createServiceCall(builder.build(), responseConverter);
	  }
	}

