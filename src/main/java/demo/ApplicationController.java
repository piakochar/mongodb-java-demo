package demo;

import demo.dao.GenericDao;
import demo.dao.ReviewsDao;
import demo.dao.AnalyticsDao;
import demo.model.Review;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("review", new Review());

        return "homepage";
    }

    @GetMapping("/data")
    public String greeting(Model model) {
        AnalyticsDao.saveHttpRequest(new Date(), "GET", "/data");

        /** db and collection being accessed -- change these to use these endpoints to use your own data */
        final String dbName = "siteData";
        final String collName = "analytics";

        final GenericDao dao = new GenericDao(dbName, collName);

        model.addAttribute("dbName", dbName);
        model.addAttribute("collName", collName);
        model.addAttribute("count", dao.countDocuments());
        model.addAttribute("review", new Review());

        return "data";
    }

    @PostMapping("/data")
    public RedirectView submitReview(@ModelAttribute Review review) {
        AnalyticsDao.saveHttpRequest(new Date(), "POST", "/data");

        review.setDate(new Date());
        ReviewsDao.saveReview(review);
        return new RedirectView("/reviews");
    }

    @GetMapping("/analytics")
    public String siteAnalytics(Model model) {

        AnalyticsDao.saveHttpRequest(new Date(), "GET", "/analytics");

        final Map<String, Integer> requestsPerType = AnalyticsDao.getNumRequestsPerType();
        model.addAttribute("requestsPerType", requestsPerType);

        return "analytics";
    }

    /**
     * NOTE: this endpoint accepts a query parameter to determine how many reviews will be displayed on the page.
     * The default value is 10, but if you want to see 5 reviews you would edit the URL to look like this:
     *
     * localhost:8081/reviews?limit=5
     * */
    @GetMapping("/reviews")
    public String reviews(
            @RequestParam(name="limit", required=false, defaultValue="10") String numReviews,
            Model model) {

        AnalyticsDao.saveHttpRequest(new Date(), "GET", "/reviews");

        model.addAttribute("reviews", ReviewsDao.findReviews(Integer.parseInt(numReviews)));
        return "reviews";
    }
}
