<script>
  import Navbar from './Navbar.svelte';
  import HeroSection from './HeroSection.svelte';
  import ComoFunciona from './ComoFunciona.svelte';
  import LiaSection from './LiaSection.svelte';
  import CardsSection from './CardsSection.svelte';
  import Footer from '../common/Footer.svelte';
  
  // Smooth scroll para las anclas
  function handleAnchorClick(e) {
    const href = e.currentTarget.getAttribute('href');
    if (href.startsWith('#')) {
      e.preventDefault();
      const element = document.querySelector(href);
      if (element) {
        element.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    }
  }
</script>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  :global(body) {
    background: #111;
    color: white;
    font-family: system-ui, -apple-system, sans-serif;
    scroll-behavior: smooth;
  }

  :global(html) {
    scroll-behavior: smooth;
  }
</style>

<Navbar {handleAnchorClick} />
<HeroSection />
<ComoFunciona />
<LiaSection />
<CardsSection {handleAnchorClick} />
<Footer />
