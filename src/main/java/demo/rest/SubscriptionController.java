package demo.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.exception.HTTP404Exception;
import demo.model.Subscription;
import demo.service.SubscriptionService;

@RestController
@RequestMapping(value = "/play-subscriptionservice/v1/subscription")
public class SubscriptionController extends AbstractRestHandler {

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public void createSubscription(@RequestBody Subscription subscription, HttpServletRequest request, HttpServletResponse response) {

		Subscription createdSubscription = this.subscriptionService.createSubscription(subscription);

		response.setHeader("Location", request.getRequestURL().append("/").append(createdSubscription.getSubscription_id()).toString());

	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Iterable<Subscription> getAllSubscription(HttpServletRequest request, HttpServletResponse response) {
		return this.subscriptionService.getAllSubscription();
	}

	@RequestMapping(value = "/{subscription_id}", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Subscription getSubscription(@PathVariable("subscription_id") String subscription_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Subscription subscription = this.subscriptionService.getSubscription(subscription_id);
		checkResourceFound(subscription);
		return subscription;
	}

	@RequestMapping(value = "/{subscription_id}", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSubscription(@PathVariable("subscription_id") String subscription_id, @RequestBody Subscription subscription, HttpServletRequest request,
			HttpServletResponse response) {

		checkResourceFound(this.subscriptionService.getSubscription(subscription_id));
		if (!(subscription_id.equals(subscription.getSubscription_id()))) {

			throw new HTTP404Exception("ID doesn't match!");
		}
		this.subscriptionService.updateSubscription(subscription);
	}

	@RequestMapping(value = "/{subscription_id}", method = RequestMethod.DELETE, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSubscription(@PathVariable("subscription_id") String subscription_id, HttpServletRequest request,
			HttpServletResponse response) {
		checkResourceFound(this.subscriptionService.getSubscription(subscription_id));
		this.subscriptionService.deleteSubscription(subscription_id);
	}
}
