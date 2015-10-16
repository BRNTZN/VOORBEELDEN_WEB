package filters;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "RequestFilter", urlPatterns = { "/*" })
public class RequestFilter implements Filter {

	private static final boolean debug = true;

	// The filter configuration object we are associated with. If
	// this value is null, this filter instance is not currently
	// configured.
	private FilterConfig filterConfig = null;

	public RequestFilter() {
	}

	private void doBeforeProcessing(ServletRequest request,
			ServletResponse response) throws IOException, ServletException {
		if (debug) {
			log("RequestFilter: request parameters before processing request");

			// Write code here to process the request and/or response before
			// the rest of the filter chain is invoked.

			// For example, a logging filter might log items on the request
			// object,
			// such as the parameters.

			for (Enumeration<String> en = request.getParameterNames(); en
					.hasMoreElements();) {
				String name = en.nextElement();
				String values[] = request.getParameterValues(name);
				int n = values.length;
				StringBuffer buf = new StringBuffer();
				buf.append(name);
				buf.append("=");
				for (int i = 0; i < n; i++) {
					buf.append(values[i]);
					if (i < n - 1)
						buf.append(",");
				}
				log(buf.toString());
			}
		}

	}

	private void doAfterProcessing(ServletRequest request,
			ServletResponse response) throws IOException, ServletException {
		if (debug) {
			log("RequestFilter: request attributes after processing request:");

			// Write code here to process the request and/or response after
			// the rest of the filter chain is invoked.

			// For example, a logging filter might log the attributes on the
			// request object after the request has been processed.

			for (Enumeration<String> en = request.getAttributeNames(); en
					.hasMoreElements();) {
				String name = en.nextElement();
				Object value = request.getAttribute(name);
				log("attribute: " + name + "=" + value.toString());

			}
		}
	}

	/**
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * 
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (debug)
			log("TimerFilter:doFilter()");

		doBeforeProcessing(request, response);

		long startTime = System.currentTimeMillis();
		chain.doFilter(request, response); // Stuur aanvraag door naar volgende filter
		long stopTime = System.currentTimeMillis();
		filterConfig.getServletContext().log(
				this.toString() + ": " + (stopTime - startTime)
						+ " milliseconds");
		doAfterProcessing(request, response);
	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {
	}

	/**
	 * Init method for this filter
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {
				log("TimerFilter:Initializing filter");
			}
		}
	}

	/**
	 * Return a String representation of this object.
	 */
	@Override
	public String toString() {
		if (filterConfig == null)
			return ("RequestFilter()");
		StringBuffer sb = new StringBuffer("RequestFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

	public void log(String msg) {
		filterConfig.getServletContext().log(msg);
	}

}
