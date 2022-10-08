package caska;

/**
 * @author Goth-Rei-Codes
 */

public class Mock {
 private String remotePath;
 private String localPath;
 private String statusCode;
 private boolean ignoreQueryParams;
 private boolean serveOnce;

 public Mock() {}

 public String getRemotePath() {
  return remotePath;
 }

 public void setRemotePath(String remotePath) {
  this.remotePath = remotePath;
 }

 public String getLocalPath() {
  return localPath;
 }

 public void setLocalPath(String localPath) {
  this.localPath = localPath;
 }

 public String getStatusCode() {
  return statusCode;
 }

 public void setStatusCode(String statusCode) {
  this.statusCode = statusCode;
 }

 public boolean isIgnoreQueryParams() {
  return ignoreQueryParams;
 }

 public void setIgnoreQueryParams(boolean ignoreQueryParams) {
  this.ignoreQueryParams = ignoreQueryParams;
 }

 public boolean isServeOnce() {
  return serveOnce;
 }

 public void setServeOnce(boolean serveOnce) {
  this.serveOnce = serveOnce;
 }
}
