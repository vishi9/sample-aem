package com.adobe.aem.rmit.core.models;

import org.junit.Test;

import com.adobe.aem.rmit.core.components.GridList;
import com.adobe.aem.rmit.core.components.RmitTags;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * The Class GetterSetterTest.
 */
public class GetterSetterTest {

	/** The Constant ACCESSOR_VALIDATOR. */
	private static final Validator ACCESSOR_VALIDATOR = ValidatorBuilder.create().with(new GetterTester())
			.with(new SetterTester()).build();

	/**
	 * Validate accessors.
	 *
	 * @param clazz
	 *            the clazz
	 */
	public static void validateAccessors(final Class<?> clazz) {
		ACCESSOR_VALIDATOR.validate(PojoClassFactory.getPojoClass(clazz));
	}

	/**
	 * Test accesors should access proper field.
	 */
	@Test
	public void testAccesors_shouldAccessProperField() {
		validateAccessors(CategoryList.class);
		validateAccessors(LeftList.class);
		validateAccessors(SocialList.class);
		validateAccessors(LegalList.class);
		validateAccessors(RightList.class);
		validateAccessors(Link.class);
		validateAccessors(GridList.class);
		validateAccessors(IconList.class);
		validateAccessors(RmitTags.class);
		validateAccessors(TabValueList.class);
		validateAccessors(MenuValueList.class);
		validateAccessors(PrimaryNavMenu.class);
		validateAccessors(PrimaryNavSubMenu.class);
		validateAccessors(FeatureContent.class);
	}
}
