import { describe, it, expect } from "vitest";
import { shallowMount } from "@vue/test-utils";

import MdlButton from "@/components/MdlButton.vue";

describe("MdlButton.vue", () => {
  it("check if icon element gets created when icon prop is defined", async () => {
    const buttonIcon = "search";
    const wrapper = shallowMount(MdlButton);
    await wrapper.setProps({
      icon: buttonIcon,
    });

    const icon = wrapper.find("i");
    expect(icon.exists()).toBe(true);
  });

  it("sets class buttonColor when prop gets passed", async () => {
    const buttonColor = "primary";
    const wrapper = shallowMount(MdlButton);
    await wrapper.setProps({
      color: buttonColor,
    });

    expect(wrapper.classes()).toContain(`mdl-button--${buttonColor}`);
  });

  it("sets class buttonType when prop gets passed", async () => {
    const buttonType = "raised";
    const wrapper = shallowMount(MdlButton);
    await wrapper.setProps({
      type: buttonType,
    });

    expect(wrapper.classes()).toContain(`mdl-button--${buttonType}`);
  });
});
