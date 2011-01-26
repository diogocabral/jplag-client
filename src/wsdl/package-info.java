/**
 * 
 * 		Compatibility level: 4
 * 		
 * 		Simple lifecycle example (using 80 kB packets for file transfer.
 * 					This method allows to show the file transfer progress and
 * 					to abort the transfer and is .NET compatible):
 * 			Call "startSubmissionUpload" with a correct set of parameters
 * 				and the first 80 kB part of the zip file containing the files
 * 				and "continueSubmissionUpload" for all remaining 80 kB parts
 * 			Optionally call "getStatus" at a minimum intervall of 10 seconds
 * 				to get status information
 * 			Call "startResultDownload" to download the first 80 kB part of the
 * 				result zip file and "continueResultDownload" for all remaining
 * 				80 kB parts
 * 			 OR call "cancelSubmission" to cancel the submission if the
 * 				result is not needed anymore or the submission was aborted
 * 				because of an error
 * 	
 * 		Simple lifecycle example (using unabortable MIME file transfer):
 * 			Call "compareSource" with a correct set of parameters to let
 * 				the server work on a zipped submission
 * 			Optionally call "getStatus" at a minimum intervall of 10 seconds
 * 				to get status information
 * 			Call "getResult" to get the zipped HTML result files
 * 				and "cancelSubmission" to delete the submission from the server
 * 				afterwards
 * 			 OR call "cancelSubmission" to cancel the submission if the
 * 				result is not needed anymore or the submission was aborted
 * 				because of an error
 * 	
 * 
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types")
package wsdl;
