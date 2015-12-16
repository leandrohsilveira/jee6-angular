package br.com.lhs.jee6angular.controllers.members;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.com.lhs.jee6angular.model.Member;
import br.com.lhs.jee6angular.service.MemberService;

@Named
@RequestScoped
public class MemberProvider implements Serializable {

	private static final long serialVersionUID = -8324019903510054567L;

	@Inject
	private MemberService memberService;

	private Converter converter;

	public List<Member> autocompleteMember(String query) {
		List<Member> members = memberService.completeByName(query, 10);
		System.out.println("Member query " + query + " results in " + members.size() + " results.");
		return members;
	}

	@Produces
	@Named("memberConverter")
	public Converter getConverter() {
		if (converter == null)
			converter = new Converter() {

			@Override
			public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
				if (object != null && object instanceof Member)
					if (((Member) object).getId() != null)
						return ((Member) object).getId().toString();
				return null;
			}

			@Override
			public Object getAsObject(FacesContext arg0, UIComponent arg1, String stringValue) {
				if (StringUtils.isNotBlank(stringValue))
					try {
						return memberService.findById(Long.valueOf(stringValue));
					} catch (NumberFormatException e) {
						// Do nothing
					}
				return null;
			}
		};
		return converter;
	}

}
